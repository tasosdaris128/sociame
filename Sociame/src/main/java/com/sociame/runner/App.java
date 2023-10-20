package com.sociame.runner;

import com.sociame.rest.Router;
import com.sociame.utils.PropertyLoader;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Main application entry. It ignites a {@link Javalin} http server which
 * listens to a port which is set on application {@link Properties} object.
 *
 * @author Tasos Daris <tasos.daris@datawise.ai>
 */
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

        ApplicationContext.properties(properties);

        var app = Javalin.create().start(ApplicationContext.port());

        Router.registerRoutes(app);

        logger.info("Server listening at {}.", ApplicationContext.port());

        Runtime.getRuntime().addShutdownHook(new ShutdownHook(app));
    }

    /**
     * Shutdown hook which will handle the graceful shutdown of the
     * application.
     */
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
