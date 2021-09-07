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

    private static final Pattern STAT_BEGIN = Pattern.compile("Name:\\s+([\\w+])\\s+([\\w+])\\s+Lives/CP:(\\d+)/(\\d+)");

    private static final Pattern STR_PATTERN = Pattern.compile("Strength:\\s+(\\d+)");
    private static final Pattern INT_PATTERN = Pattern.compile("Intellect:\\s+(\\d+)");
    private static final Pattern WIL_PATTERN = Pattern.compile("Willpower:\\s+(\\d+)");
    private static final Pattern AGI_PATTERN = Pattern.compile("Agility:\\s+(\\d+)");
    private static final Pattern HEA_PATTERN = Pattern.compile("Health:\\s+(\\d+)");
    private static final Pattern CHA_PATTERN = Pattern.compile("Charm:\\s+(\\d+)");

    private static final Pattern HITS_PATTERN = Pattern.compile("Hits:\\s+(\\d+)/(\\d+)");
    private static final Pattern MANA_PATTERN = Pattern.compile("Mana:\\s+(\\d+)/(\\d+)");
    private static final Pattern ARMOR_PATTERN = Pattern.compile("Armour Class:\\s+(\\d+)/(\\d+)");
    private static final Pattern SC_PATTERN = Pattern.compile("Spellcasting:\\s+(\\d+)");

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("data.lines", message -> {
            String line = (String) message.body();
            Matcher matcher = STAT_BEGIN.matcher(line);
            if (matcher.find()) {
                LOGGER.info("Exp pattern hit: {} exp needed", matcher.group(1));
            }
        });
    }
}
