package edu.epam.webproject.controller.listener;

import edu.epam.webproject.model.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.destroy();
    }
}
