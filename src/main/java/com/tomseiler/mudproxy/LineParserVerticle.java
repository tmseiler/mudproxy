package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LineParserVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(LineParserVerticle.class);

    private static final String ESCAPE = "\033\\[.*?m";

    private static final Pattern escapePattern = Pattern.compile(ESCAPE);


    private Buffer mainBuffer = Buffer.buffer(1024);

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("data.raw.incoming", message -> {
            Buffer buffer = (Buffer) message.body();
            mainBuffer.appendBuffer(buffer);
            parseLines();
        });
    }

    private void parseLines() {
        StringBuffer lineBuffer = new StringBuffer();

        for (int i = 0; i < mainBuffer.length(); i++) {
            String string = mainBuffer.getString(i, i + 1);
            if (string.equals("\n")) {
                String parsedLine = lineBuffer.toString();

                Matcher matcher = escapePattern.matcher(parsedLine);

                if (matcher.find()) {
                    parsedLine = matcher.replaceAll("");
                }

                LOGGER.debug("Parsed incoming line: {}", parsedLine);
                vertx.eventBus().publish("data.lines", parsedLine);

                lineBuffer.delete(0, lineBuffer.length());
            } else {
                lineBuffer.append(string);
            }
        }
        mainBuffer = Buffer.buffer(lineBuffer.toString());
    }
}
