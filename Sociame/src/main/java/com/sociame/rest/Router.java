package com.sociame.rest;

import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

/**
 * Registers all the REST routes of the application.
 *
 * @author Tasos Daris <tasos.daris@datawise.ai>
 */
public abstract class Router {

    private final static Logger logger = LogManager.getLogger(Router.class);

    /**
     * Sets the routes in a given {@link Javalin} application instance.
     *
     * @param app A {@link Javalin} instance.
     */
    public static void registerRoutes(Javalin app) {
        logger.info("Registering application REST routes...");

        app.routes(() -> {
            path("ping", () -> get(context -> context.result("pong")));
        });
    }

}
