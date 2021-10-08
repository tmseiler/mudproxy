package com.tomseiler.mudproxy.parsers;

import com.tomseiler.mudproxy.models.parsed.PlayerStats;
import com.tomseiler.mudproxy.models.parsed.PlayerStatsBuilder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tomseiler.mudproxy.util.Topics.LINES_STRIPPED;

public class StatParserVerticle extends AbstractVerticle {
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

    private static final Logger LOGGER = LoggerFactory.getLogger(StatParserVerticle.class);

    private static final Pattern STAT_LINE_1 = Pattern.compile("Name:\\s+(\\w+)\\s+(\\w+)\\s+Lives/CP:\\s+(\\d+)/(\\d+)");
    private static final Pattern STAT_LINE_2 = Pattern.compile(
            "Race:\\s+(\\w+(?:\\s\\w+)?)\\s+Exp:\\s+(\\d+)\\s+Perception:\\s+(\\d+)");
    private static final Pattern STAT_LINE_3 = Pattern.compile(
            "Class:\\s+(\\w+)\\s+Level:\\s+(\\d+)\\s+Stealth:\\s+(\\d+)");
    private static final Pattern STAT_LINE_4 = Pattern.compile(
            "Hits:\\s+(\\d+)/(\\d+)\\s+Armour Class:\\s+(\\d+)/(\\d+)\\s+Thievery:\\s+(\\d+)");
    private static final Pattern STAT_LINE_5 = Pattern.compile(
            "Mana:\\s+([\\d+])/([\\d+])\\s+Spellcasting:\\s+(\\d+)\\s+Traps:\\s+(\\d+)");
    private static final Pattern STAT_LINE_6 = Pattern.compile("\\s+Picklocks:\\s+(\\d+)");
    private static final Pattern STAT_LINE_7 = Pattern.compile(
            "Strength:\\s+(\\d+)\\s+Agility:\\s+(\\d+)\\s+Tracking:\\s+(\\d+)");
    private static final Pattern STAT_LINE_8 = Pattern.compile(
            "Intellect:\\s+(\\d+)\\s+Health:\\s+(\\d+)\\s+Martial Arts:\\s+(\\d+)");
    private static final Pattern STAT_LINE_9 = Pattern.compile(
            "Willpower:\\s+(\\d+)\\s+Charm:\\s+(\\d+)\\s+MagicRes:\\s+(\\d+)");

    private static final List<Pattern> patternList = Arrays.asList(STAT_LINE_1,
            STAT_LINE_2,
            STAT_LINE_3,
            STAT_LINE_4,
            STAT_LINE_5,
            STAT_LINE_6,
            STAT_LINE_7,
            STAT_LINE_8,
            STAT_LINE_9);

    private PlayerStatsBuilder builder = null;
    private int patternIndex = 0;

    @Override
    public void start() throws Exception {
        LOGGER.info("{} deployed", getClass().getSimpleName());
        vertx.eventBus().<String>consumer(LINES_STRIPPED, this::handleLine);
    }

    private void handleLine(Message<String> message) {
        if (builder == null) {
            patternIndex = 0;
        }

        String line = message.body();

        Matcher matcher = patternList.get(patternIndex).matcher(line);

        // todo always reset if first one matches

        if (matcher.find()) {
            LOGGER.debug("Stat pattern hit: {}", matcher.group(0));
            switch (patternIndex) {
                case 0:
                    builder = new PlayerStatsBuilder();
                    builder.firstName(matcher.group(1));
                    builder.lastName(matcher.group(2));
                    builder.lives(Integer.parseInt(matcher.group(3)));
                    builder.cp(Integer.parseInt(matcher.group(4)));
                    patternIndex++;
                    break;
                case 1:
                    builder.race(matcher.group(1));
                    builder.exp(Integer.parseInt(matcher.group(2)));
                    builder.perception(Integer.parseInt(matcher.group(3)));
                    patternIndex++;
                    break;
                case 2:
                    builder.playerClass(matcher.group(1));
                    builder.level(Integer.parseInt(matcher.group(2)));
                    builder.stealth(Integer.parseInt(matcher.group(3)));
                    patternIndex++;
                    break;
                case 3:
                    builder.currentHits(Integer.parseInt(matcher.group(1)));
                    builder.maxHits(Integer.parseInt(matcher.group(2)));
                    builder.ac(Integer.parseInt(matcher.group(3)));
                    builder.dr(Integer.parseInt(matcher.group(4)));
                    builder.thievery(Integer.parseInt(matcher.group(4)));
                    patternIndex++;
                    break;
                case 4:
                    builder.currentMana(Integer.parseInt(matcher.group(1)));
                    builder.maxMana(Integer.parseInt(matcher.group(2)));
                    builder.sc(Integer.parseInt(matcher.group(3)));
                    builder.traps(Integer.parseInt(matcher.group(4)));
                    patternIndex++;
                    break;
                case 5:
                    builder.picklocks(Integer.parseInt(matcher.group(1)));
                    patternIndex++;
                    break;
                case 6:
                    builder.strength(Integer.parseInt(matcher.group(1)));
                    builder.agility(Integer.parseInt(matcher.group(2)));
                    builder.tracking(Integer.parseInt(matcher.group(3)));
                    patternIndex++;
                    break;
                case 7:
                    builder.intellect(Integer.parseInt(matcher.group(1)));
                    builder.health(Integer.parseInt(matcher.group(2)));
                    builder.martialArts(Integer.parseInt(matcher.group(3)));
                    patternIndex++;
                    break;
                case 8:
                    builder.willpower(Integer.parseInt(matcher.group(1)));
                    builder.charm(Integer.parseInt(matcher.group(2)));
                    builder.magicRes(Integer.parseInt(matcher.group(3)));
                    PlayerStats stats = builder.build();
                    LOGGER.info("Stats detected: {}", stats);
                    builder = null;
                    patternIndex = 0;
                    break;
                default:
            }

        }
    }
}
