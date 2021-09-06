package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer netServer = vertx.createNetServer(options);
        LOGGER.info("Server started.");
        System.out.println("This should be logged");

        // user tomtest
        // pass whatup

        netServer.connectHandler(serverSocket -> {
            LOGGER.info("Accepted new connection.");
            ProxyVerticle proxyVerticle = new ProxyVerticle(serverSocket, "71.201.83.24", 2020);
            vertx.deployVerticle(proxyVerticle);
        });
        netServer.listen();

        vertx.deployVerticle(new LineParserVerticle());
    }
}
