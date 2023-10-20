package com.sociame.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * An application wide context holder.
 *
 * @author Tasos Daris <tasos.daris@datawise.ai>
 */
public class ApplicationContext {

    private final static Logger logger = LogManager.getLogger(ApplicationContext.class);

    private static ApplicationContext instance;

    private volatile Properties properties;

    private volatile int httpPort;

    private ApplicationContext() {}

    public static synchronized ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }

        return instance;
    }

    public static synchronized void properties(Properties properties) {
        getInstance().setProperties(properties);
    }

    public static synchronized Properties properties() {
        return getInstance().getProperties();
    }

    public static synchronized int port() {
        return getInstance().getHttpPort();
    }

    private void setProperties(Properties properties) {
        this.properties = properties;

        try {
            this.httpPort = Integer.parseInt(properties.getProperty("APP_PORT", "5000"));
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            this.httpPort = 5000;
        }
    }

    private Properties getProperties() {
        return this.properties;
    }

    private int getHttpPort() {
        return this.httpPort;
    }

}
