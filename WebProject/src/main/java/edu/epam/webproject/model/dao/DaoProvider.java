package edu.epam.webproject.model.dao;

import edu.epam.webproject.model.dao.impl.OfferDaoImpl;
import edu.epam.webproject.model.dao.impl.ReservationDaoImpl;
import edu.epam.webproject.model.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final OfferDao offerDao = OfferDaoImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoImpl.getInstance();

    private DaoProvider(){}

    public static DaoProvider getInstance(){
        return instance;
    }

    public OfferDao getOfferDao(){ return offerDao; }
    public UserDao getUserDao(){
        return userDao;
    }
    public ReservationDao getReservationDao(){ return reservationDao; }
}
