package edu.epam.webproject.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 4;
    private static ConnectionPool instance = new ConnectionPool();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private ConnectionPool(){
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try{
                Connection connection = ConnectionFactory.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.put(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Unable to create connection: " + e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (freeConnections.isEmpty()){
            logger.log(Level.FATAL, "Unable to create all connections");
            throw new RuntimeException("Unable to create all connections");
        }
    }
    public static ConnectionPool getInstance(){
        while (instance == null){
            if (isCreated.compareAndSet(false, true)){
                instance = new ConnectionPool();
            }
        }
        return instance;
    }
    public Connection getConnection(){
        ProxyConnection connection = null;
        try{
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }
    public void releaseConnection(Connection connection){
        if (!(connection instanceof ProxyConnection)){
            logger.log(Level.FATAL, "Incorrect connection: " + connection);
            throw new RuntimeException("Incorrect connection: " + connection);
        }
        usedConnections.remove(connection);
        try {
            freeConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void destroy(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try{
                freeConnections.take().reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.log(Level.ERROR, "Connection wasn't deleted");
            }
        }
    }
    private void deregisterDrivers(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()){
            Driver driver = drivers.nextElement();
            try{
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                logger.log(Level.ERROR, "Unable to deregister driver: " + driver, throwables);
            }
        }
    }
}
