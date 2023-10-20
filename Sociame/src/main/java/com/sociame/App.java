package com.sociame;

import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private final static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        var app = Javalin.create().start(42069);

        app.get("/ping", ctx -> ctx.result("pong"));

        logger.info("Server listening at 42069.");

        Runtime.getRuntime().addShutdownHook(new ShutdownHook(app));
    }

    private static class ShutdownHook extends Thread {

        Javalin app;

        ShutdownHook(Javalin app) {
            this.app = app;
        }

        @Override
        public void run() {
            logger.info("Gracefully shutting down the server. Goodbye!");
            app.stop();
        }

    }

}
