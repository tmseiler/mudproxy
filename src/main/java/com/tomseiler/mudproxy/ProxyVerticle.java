package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyVerticle extends AbstractVerticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProxyVerticle.class);
  private final NetSocket serverSocket;
  private final String host;
  private final int port;

  public ProxyVerticle(NetSocket serverSocket, String host, int port) {
    this.serverSocket = serverSocket;
    this.host = host;
    this.port = port;
  }

  @Override
  public void start() throws Exception {
    LOGGER.info("ProxyVerticle started.");
    NetClient netClient = vertx.createNetClient();
    netClient.connect(port, host, asyncResult -> {
      if (asyncResult.succeeded()) {
        LOGGER.info("Connected to the BBS!");
        NetSocket clientSocket = asyncResult.result();
        clientSocket.handler(buffer -> {
          // Tee incoming data from the BBS here
          vertx.eventBus().publish("data.raw.incoming", buffer);
          serverSocket.write(buffer);
        });
        serverSocket.handler(clientSocket::write);
      } else {
        LOGGER.info("Failed to connect: " + asyncResult.cause().getMessage());
      }
    });
  }
}
