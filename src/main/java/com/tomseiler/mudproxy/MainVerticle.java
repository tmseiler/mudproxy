package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer netServer = vertx.createNetServer(options);

        // user tomtest
        // pass whatup

        netServer.connectHandler(serverSocket -> {
            System.out.println("Accepted new connection.");
            ProxyVerticle proxyVerticle = new ProxyVerticle(serverSocket, "71.201.83.24", 2020);
            vertx.deployVerticle(proxyVerticle);
        });
        netServer.listen();

        vertx.deployVerticle(new LineParserVerticle());
    }
}
