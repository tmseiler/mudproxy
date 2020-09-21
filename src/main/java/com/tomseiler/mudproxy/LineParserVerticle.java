package com.tomseiler.mudproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;


public class LineParserVerticle extends AbstractVerticle {
  private Buffer mainBuffer = Buffer.buffer(1024);

  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer("data.incoming", message -> {
      Buffer buffer = (Buffer) message.body();
      mainBuffer.appendBuffer(buffer);
      parseLines();
    });
  }

  private void parseLines() {
    StringBuffer lineBuffer = new StringBuffer();

    for (int i = 0; i < mainBuffer.length(); i++) {
      String string = mainBuffer.getString(i, i + 1);
      if (string.equals("\n")) {
        vertx.eventBus().publish("data.lines", lineBuffer.toString());
        lineBuffer = new StringBuffer();
      } else {
        lineBuffer.append(string);
      }
    }
    mainBuffer = Buffer.buffer(lineBuffer.toString());
  }
}
