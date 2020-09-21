package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

public class ProxyVerticle extends AbstractVerticle {

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
    System.out.println("ProxyVerticle started.");
    NetClient netClient = vertx.createNetClient();
    netClient.connect(port, host, res -> {
      if (res.succeeded()) {
        System.out.println("Connected to the BBS!");
        NetSocket clientSocket = res.result();
        clientSocket.handler(data1 -> {
          // Tee incoming data from the BBS here
          vertx.eventBus().publish("data.bbs.incoming", data1);
          serverSocket.write(data1);
        });
        serverSocket.handler(clientSocket::write);
      } else {
        System.out.println("Failed to connect: " + res.cause().getMessage());
      }
    });
  }
}
