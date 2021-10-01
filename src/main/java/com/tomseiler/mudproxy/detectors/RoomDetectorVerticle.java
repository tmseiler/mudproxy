package com.tomseiler.mudproxy.detectors;

import com.tomseiler.mudproxy.models.RoomBuilder;
import com.tomseiler.mudproxy.util.Ansi;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tomseiler.mudproxy.util.Ansi.ESCAPE;
import static com.tomseiler.mudproxy.util.Topics.RAW_LINES;

public class RoomDetectorVerticle extends AbstractVerticle {
    /*
    Lava Tube
    You notice 5 gold crowns here.
    Also here: fat salamander.
    Obvious exits: east, northwest
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomDetectorVerticle.class);

    public static final Pattern ROOM_NAME = Pattern.compile(String.format("%s(.*)%s?", Ansi.BOLD_CYAN_RE, ESCAPE));
    public static final Pattern OBVIOUS_EXITS = Pattern.compile(String.format("Obvious exits: (.*)%s?", ESCAPE));

    private RoomBuilder roomBuilder;

    @Override
    public void start() {
        LOGGER.info("{} deployed", getClass().getSimpleName());

        vertx.eventBus().<String>consumer(RAW_LINES, message -> {
            String line = message.body();

            Matcher nameMatcher = ROOM_NAME.matcher(line);
            Matcher exitMatcher = OBVIOUS_EXITS.matcher(line);
            if (nameMatcher.find()) {
                String roomName = Ansi.stripAnsi(nameMatcher.group(1));
                LOGGER.debug("Found room name: {}", roomName);
                roomBuilder = new RoomBuilder().name(roomName);
            }

            if (exitMatcher.find()) {
                String exits = Ansi.stripAnsi(exitMatcher.group(1));
                LOGGER.debug("Found exits: {}", exits);
                StringTokenizer tokenizer = new StringTokenizer(exits, ",");
                Set<String> exitSet = new HashSet<>();
                tokenizer.asIterator().forEachRemaining(e -> exitSet.add(((String) e).trim()));
                roomBuilder.exits(exitSet);
                LOGGER.info("Found room: {}", roomBuilder.createRoom());
            }
        });
    }
}
