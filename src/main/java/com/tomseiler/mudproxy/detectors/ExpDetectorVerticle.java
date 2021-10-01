package com.tomseiler.mudproxy.detectors;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tomseiler.mudproxy.util.Topics.STRIPPED_LINES;

public class ExpDetectorVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpDetectorVerticle.class);

    private static final Pattern EXP_PATTERN = Pattern.compile("Exp: (\\d+) Level: (\\d+) Exp needed for next level: (\\d+) \\((\\d+)\\) \\[(\\d+)%]");

    @Override
    public void start() throws Exception {
        LOGGER.info("{} deployed", getClass().getSimpleName());

        vertx.eventBus().<String>consumer(STRIPPED_LINES, message -> {
            String line = message.body();
            Matcher matcher = EXP_PATTERN.matcher(line);
            if (matcher.find()) {
                LOGGER.info("Exp pattern hit: {} exp needed", matcher.group(1));
            }
        });
    }
}
