package edu.epam.webproject.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Logger logger = LogManager.getLogger();
    private static final String URL = "jdbc:mysql://localhost:3306/webproject";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Properties properties = new Properties();
    private static final String RESOURCE_FILE = "/db.properties";
    static {
        try(InputStream stream = ConnectionFactory.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)){
            Class.forName(DRIVER);
            properties.load(stream);
        }catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Unable to register driver: " + DRIVER);
            throw new RuntimeException("Unable to register driver: \" + driverName");
        } catch (IOException e){
            logger.log(Level.FATAL, "Unable to find properties file: " + RESOURCE_FILE);
            throw new RuntimeException("Unable to find properties file: " + RESOURCE_FILE);
        }
    }
    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, properties);
    }
}
