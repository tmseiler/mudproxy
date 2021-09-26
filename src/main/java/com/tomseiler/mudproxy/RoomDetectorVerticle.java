package com.tomseiler.mudproxy;

import com.tomseiler.mudproxy.util.Ansi;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tomseiler.mudproxy.util.Topics.RAW_LINES;

public class RoomDetectorVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomDetectorVerticle.class);

    public static final Pattern ROOM_NAME = Pattern.compile(String.format("%s(.*?)", Ansi.BOLD_CYAN));

    @Override
    public void start() throws Exception {
        LOGGER.info("{} deployed", getClass().getSimpleName());
        vertx.eventBus().consumer(RAW_LINES, message -> {
            String line = (String) message.body();

            Matcher matcher = ROOM_NAME.matcher(line);
            if (matcher.find()) {
                LOGGER.debug("Found room: {}", matcher.group(0));
            }
        });
    }
}
