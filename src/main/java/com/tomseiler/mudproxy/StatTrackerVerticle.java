package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatTrackerVerticle extends AbstractVerticle {
    /*
    Name: Proxy Testing                    Lives/CP:      9/0
    Race: Gaunt One   Exp: 0               Perception:     63
    Class: Ranger     Level: 1             Stealth:        46
    Hits:    41/41    Armour Class:   0/0  Thievery:        0
    Mana:     8/8     Spellcasting: 43     Traps:           0
                                       Picklocks:       0
    Strength:  55     Agility: 50          Tracking:       30
    Intellect: 60     Health:  65          Martial Arts:   14
    Willpower: 50     Charm:   30          MagicRes:       52
    */

    private static final Logger LOGGER = LoggerFactory.getLogger(StatTrackerVerticle.class);

    private static final Pattern STAT_LINE_1 = Pattern.compile("Name:\\s+([\\w]+)\\s+([\\w]+)\\s+Lives/CP:(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_2 = Pattern.compile("Race:\\s+([\\w\\s]+)\\s+Exp:\\s+(\\d+)\\s+Perception:\\s+(\\d+)");
    private static final Pattern STAT_LINE_3 = Pattern.compile("Class:\\s+([\\w]+)\\s+Level:\\s+(\\d+)\\s+Stealth:\\s+(\\d+)");
    private static final Pattern STAT_LINE_4 = Pattern.compile("Hits:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_5 = Pattern.compile("Mana:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_6 = Pattern.compile("Picklocks:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_7 = Pattern.compile("Strength:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_8 = Pattern.compile("Intellect:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_9 = Pattern.compile("Willpower:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("data.lines", message -> {
            String line = (String) message.body();
            Matcher matcher = STAT_LINE_1.matcher(line);
            if (matcher.find()) {
                LOGGER.info("Exp pattern hit: {} exp needed", matcher.group(1));
            }
        });
    }
}
