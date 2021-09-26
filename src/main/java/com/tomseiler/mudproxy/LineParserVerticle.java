package com.tomseiler.mudproxy;

import com.tomseiler.mudproxy.util.Ansi;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tomseiler.mudproxy.util.Topics.*;


public class LineParserVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(LineParserVerticle.class);

    private Buffer mainBuffer = Buffer.buffer(1024);

    @Override
    public void start() throws Exception {
        LOGGER.info("{} deployed", getClass().getSimpleName());

        vertx.eventBus().consumer(RAW_SERVER_DATA, message -> {
            Buffer buffer = (Buffer) message.body();
            mainBuffer.appendBuffer(buffer);
            parseLines();
        });
    }

    private void parseLines() {
        StringBuilder lineBuffer = new StringBuilder();

        for (int i = 0; i < mainBuffer.length(); i++) {
            String string = mainBuffer.getString(i, i + 1);
            if (string.equals("\n")) {
                String parsedLine = lineBuffer.toString();

                vertx.eventBus().publish(RAW_LINES, parsedLine);

                String strippedLine;
                strippedLine = Ansi.stripAnsi(parsedLine);

                LOGGER.debug("Parsed incoming line: {}", strippedLine);
                vertx.eventBus().publish(STRIPPED_LINES, strippedLine);

                lineBuffer.delete(0, lineBuffer.length());
            } else {
                lineBuffer.append(string);
            }
        }
        mainBuffer = Buffer.buffer(lineBuffer.toString());
    }
}
