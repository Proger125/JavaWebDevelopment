package edu.epam.webproject.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Logger logger = LogManager.getLogger(ConnectionFactory.class);
    private static final String URL = "jdbc:mysql://localhost:3306/webproject";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Properties properties = new Properties();
    private static final String RESOURCE_FILE = "\\db.properties";
    static {
        File currentClass = null;
        try {
            currentClass = new File(URLDecoder.decode(ConnectionFactory.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(currentClass.getAbsolutePath());
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
