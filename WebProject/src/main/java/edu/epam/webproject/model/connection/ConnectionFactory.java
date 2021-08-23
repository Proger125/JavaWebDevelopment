package edu.epam.webproject.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory class that creates connections to database
 */
class ConnectionFactory {
    /**
     * @see Logger
     */
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    /**
     * Database url
     */
    private static final String URL;

    /**
     * Database url property name
     */
    private static final String DB_URL = "db.url";

    /**
     * Database driver property name
     */
    private static final String DB_DRIVER = "db.driver";

    /**
     * Property file with all config info
     */
    private static final Properties properties = new Properties();

    /**
     * Relative path to resource file
     */
    private static final String RESOURCE_FILE = "\\db.properties";

    static {
        String driver = null;
        try(InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)){
            properties.load(stream);
            driver = properties.getProperty(DB_DRIVER);
            Class.forName(driver);

        }catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Unable to register driver: " + driver);
            throw new RuntimeException("Unable to register driver: \" + driverName", e);
        } catch (IOException e){
            logger.log(Level.FATAL, "Unable to find properties file: " + RESOURCE_FILE);
            throw new RuntimeException("Unable to find properties file: " + RESOURCE_FILE, e);
        }
        URL = properties.getProperty(DB_URL);
    }

    /**
     * Creates connection to database
     * @return {@link Connection} object
     * @throws SQLException when problems with database occurs
     */
    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, properties);
    }
}
