package com.sociame.runner;

import com.sociame.utils.PropertyLoader;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {

    private final static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        Properties properties;

        try {
            FileInputStream in = new FileInputStream("application.properties");
            properties = PropertyLoader.loadProperties(in);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Unable to load application properties.");
        }

        int port = Integer.parseInt(properties.getProperty("APP_PORT", "5000"));

        var app = Javalin.create().start(port);

        app.get("/ping", ctx -> ctx.result("pong"));

        logger.info("Server listening at {}.", port);

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
