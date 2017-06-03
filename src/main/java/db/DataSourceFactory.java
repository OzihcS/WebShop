package db;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads params and create {@link DataSource}
 */
public class DataSourceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceFactory.class);
    private static final String DB_CONFIG_FILE = "/db.properties";

    /**
     * Used to create {@link DataSource}.
     *
     * @return data source.
     */
    public static DataSource getDataSource() {
        Properties properties = new Properties();
        MysqlDataSource mysqlDataSource = null;
        try (InputStream inputStream = Query.class.getResourceAsStream(DB_CONFIG_FILE)) {
            properties.load(inputStream);

            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL((String) properties.get(Constants.DBConfigurations.URL));
            mysqlDataSource.setUser((String) properties.get(Constants.DBConfigurations.USER_NAME));
            mysqlDataSource.setPassword((String) properties.get(Constants.DBConfigurations.PASSWORD));

        } catch (IOException e) {
            LOGGER.error("Occurred exception when tried open connection with cause: '{}'", e.getMessage());
        }

        return mysqlDataSource;
    }

}
