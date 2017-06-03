package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Contains all queries used in repositories and stored in specified properties file.
 */
public class Query {

    private static final String QUERY_FILE = "/query.properties";
    private static final Properties PROPERTIES;
    private static final Logger LOGGER = LoggerFactory.getLogger(Query.class);

    static {
        PROPERTIES = new Properties();
        try (InputStream inputStream = Query.class.getResourceAsStream(QUERY_FILE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Cannot load file: '{}': ", QUERY_FILE);
        }
    }

    /**
     * Gets a query by {@code key}.
     *
     * @param key key of the query to find
     * @return found query
     */
    public static String get(String key) {
        return (String) PROPERTIES.get(key);
    }

}
