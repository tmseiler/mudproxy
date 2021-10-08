package com.tomseiler.mudproxy.parsers;

import com.tomseiler.mudproxy.models.PlayerExp;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tomseiler.mudproxy.util.Topics.LINES_STRIPPED;
import static com.tomseiler.mudproxy.util.Topics.PARSED_EXP;

public class ExpParserVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpParserVerticle.class);

    private static final Pattern EXP_PATTERN = Pattern.compile("Exp: (\\d+) Level: (\\d+) Exp needed for next level: (\\d+) \\((\\d+)\\) \\[(\\d+)%]");

    @Override
    public void start() {
        LOGGER.info("{} deployed", getClass().getSimpleName());

        vertx.eventBus().<String>consumer(LINES_STRIPPED, message -> {
            String line = message.body();
            Matcher matcher = EXP_PATTERN.matcher(line);
            if (matcher.find()) {
                PlayerExp playerExp = new PlayerExp(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)),
                        Integer.parseInt(matcher.group(5))
                );
                LOGGER.info("Found exp: {}", playerExp);
                vertx.eventBus().publish(PARSED_EXP, playerExp);
            }
        });
    }
}
