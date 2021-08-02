package edu.epam.webproject.model.dao;

import edu.epam.webproject.model.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private static final UserDao userDao = UserDaoImpl.getInstance();

    private DaoProvider(){}

    public static DaoProvider getInstance(){
        return instance;
    }

    public UserDao getUserDao(){
        return userDao;
    }
}
