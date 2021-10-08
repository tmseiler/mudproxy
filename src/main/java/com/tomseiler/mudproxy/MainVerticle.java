package com.tomseiler.mudproxy;

import com.tomseiler.mudproxy.parsers.ExpParserVerticle;
import com.tomseiler.mudproxy.parsers.MobParserVerticle;
import com.tomseiler.mudproxy.parsers.RoomParserVerticle;
import com.tomseiler.mudproxy.parsers.StatParserVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);
    private String host;
    private int port;

    @Override
    public void start(Promise<Void> startPromise) {
        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer netServer = vertx.createNetServer(options);

        ConfigRetriever retriever = ConfigRetriever.create(vertx);

        retriever.getConfig(json -> {
            JsonObject result = json.result();
            host = result.getString("host");
            port = result.getInteger("port");
            LOGGER.info("Proxy config loaded: {}:{}", host, port);
        });

        LOGGER.info("Server started.");

        netServer.connectHandler(serverSocket -> {
            LOGGER.info("Accepted new connection.");
            ProxyVerticle proxyVerticle = new ProxyVerticle(serverSocket, host, port);
            vertx.deployVerticle(proxyVerticle);
        });
        netServer.listen();
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
        vertx.deployVerticle(new LineParserVerticle());
        vertx.deployVerticle(new ExpParserVerticle());
        vertx.deployVerticle(new StatParserVerticle());
        vertx.deployVerticle(new RoomParserVerticle());
        vertx.deployVerticle(new MobParserVerticle());
    }
}
