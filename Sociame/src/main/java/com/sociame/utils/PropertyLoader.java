package com.sociame.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for retrieving the application properties, based on a given
 * {@link InputStream}.
 *
 * @author Tasos Daris <tasos.daris@datawise.ai>
 */
public abstract class PropertyLoader {

    /**
     * Returns the values of an application.properties file.
     *
     * @param in            A given {@link InputStream} with a predefined file path.
     * @return              Returns a {@link Properties} object.
     * @throws IOException  In case of in.close()
     */
    public static Properties loadProperties(InputStream in) throws IOException {
        Properties properties = new Properties();

        if (in == null) {
            throw new RuntimeException("InputStream is null");
        }

        properties.load(in);

        in.close();

        return properties;
    }


}
