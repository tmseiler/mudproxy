package com.tomseiler.mudproxy.detectors;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tomseiler.mudproxy.util.Topics.LINES_STRIPPED;

public class MobDetectorVerticle extends AbstractVerticle {
    /*
    Lava Tube
    Also here: angry salamander, salamander.
    Obvious exits: east, southwest
    */
    private static final Logger LOGGER = LoggerFactory.getLogger(MobDetectorVerticle.class);

    StringBuffer alsoHere = null;

    public static final Pattern ALSO_HERE = Pattern.compile("Also here: (.*)");
    public static final Pattern OBVIOUS_EXITS = Pattern.compile("Obvious exits:");

    public static final Pattern ADJECTIVES_PATTERN = Pattern.compile("fierce|short|fat|happy|tall|large|big|nasty|angry|small");


    @Override
    public void start() throws Exception {
        LOGGER.info("{} deployed", getClass().getSimpleName());

        vertx.eventBus().<String>consumer(LINES_STRIPPED, message -> {
            Matcher alsoHereMatcher = ALSO_HERE.matcher(message.body());
            Matcher obviousExitsMatcher = OBVIOUS_EXITS.matcher(message.body());
            if (alsoHereMatcher.find()) {
                // we are in the opening line
                alsoHere = new StringBuffer(alsoHereMatcher.group(1) + " ");
            } else if (alsoHere != null && obviousExitsMatcher.find()) {
                // we can stop looking and build the string
                List<String> names = parseNames(alsoHere.toString());
                LOGGER.info("Found mobs: {}", names);
                alsoHere = null;
            } else if (alsoHere != null) {
                // we're on a subsequent line
                alsoHere.append(message.body()).append(" ");
            }
        });
    }

    private List<String> parseNames(String rawString) {
        var names = new ArrayList<String>();

        String toParse = rawString.replace("Also here: ", "");
        toParse = toParse.replace(".", "");

        StringTokenizer tokenizer = new StringTokenizer(toParse, ",");
        tokenizer.asIterator().forEachRemaining(token -> {
            Matcher matcher = ADJECTIVES_PATTERN.matcher((String) token);
            names.add(matcher.replaceFirst("").trim());
        });
        return names;
    }
}
