package edu.epam.webproject.model.dao;

import edu.epam.webproject.model.dao.impl.OfferDaoImpl;
import edu.epam.webproject.model.dao.impl.ReservationDaoImpl;
import edu.epam.webproject.model.dao.impl.UserDaoImpl;

/**
 * Factory class that provides implementations of all DAO interfaces
 */
public class DaoProvider {
    /**
     * Instance of the class
     */
    private static final DaoProvider instance = new DaoProvider();

    /**
     * An object of {@link UserDaoImpl}
     */
    private static final UserDao userDao = UserDaoImpl.getInstance();

    /**
     * An object of {@link OfferDaoImpl}
     */
    private static final OfferDao offerDao = OfferDaoImpl.getInstance();

    /**
     * An object of {@link ReservationDaoImpl}
     */
    private static final ReservationDao reservationDao = ReservationDaoImpl.getInstance();

    /**
     * Private constructor without parameters
     */
    private DaoProvider(){}

    /**
     * Returns the instance of {@link DaoProvider}
     * @return {@link DaoProvider} object
     */
    public static DaoProvider getInstance(){
        return instance;
    }

    /**
     * Returns the instance of {@link OfferDao}
     * @return {@link OfferDao} object
     */
    public OfferDao getOfferDao(){ return offerDao; }

    /**
     * Returns the instance of {@link UserDao}
     * @return {@link UserDao} object
     */
    public UserDao getUserDao(){ return userDao; }

    /**
     * Returns the instance of {@link ReservationDao}
     * @return {@link ReservationDao} object
     */
    public ReservationDao getReservationDao(){ return reservationDao; }
}
