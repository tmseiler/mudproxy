package com.tomseiler.mudproxy.lookup;

import io.vertx.core.AbstractVerticle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.tomseiler.mudproxy.util.Topics.PARSED_MOBS;

public class MobLookupVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(MobLookupVerticle.class);


    @Override
    public void start() throws Exception {
        LOGGER.info("{} deployed", getClass().getSimpleName());

        Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/data-v1.11p.mdb");
        Jdbi jdbi = Jdbi.create(conn);
        jdbi.installPlugin(new SqlObjectPlugin());
        LOGGER.info("Connected to database");

        vertx.eventBus().<String>consumer(PARSED_MOBS, mobMessage -> {
            String mobName = mobMessage.body();
            LOGGER.info("Looking up: {}", mobName);
        });
    }
}
