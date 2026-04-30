package com.shea.rpc.core.server;

import io.vertx.core.Vertx;

/**
 * @author : Shea.
 * @since : 2026/4/29 19:58
 */
public class VertxHttpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        server.requestHandler(new HttpServerHandler());
        server.listen(port,res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + res.cause());
            }
        });
    }
}
