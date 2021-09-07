package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatTrackerVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatTrackerVerticle.class);

    private static final Pattern EXP_PATTERN = Pattern.compile("Exp:.*?(\\d+)");
//    private static final Pattern EXP_PATTERN = Pattern.compile("Exp: (\\d+) Level: (\\d+) Exp needed for next level: (\\d+) \\((\\d+)\\) \\[(\\d+)%]");

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("data.lines", message -> {
            String line = (String) message.body();
            Matcher matcher = EXP_PATTERN.matcher(line);
            if (matcher.find()) {
                LOGGER.info("Exp pattern hit: {} exp needed", matcher.group(1));
            } else {
                LOGGER.info("No exp line match");
            }
        });
    }
}
