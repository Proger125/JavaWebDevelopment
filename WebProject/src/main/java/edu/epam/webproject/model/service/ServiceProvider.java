package edu.epam.webproject.model.service;

import edu.epam.webproject.model.service.impl.OfferServiceImpl;
import edu.epam.webproject.model.service.impl.ReservationServiceImpl;
import edu.epam.webproject.model.service.impl.UserServiceImpl;

/**
 * Factory class that provides implementations of all Service interfaces
 */
public class ServiceProvider {
    /**
     * Instance of the class
     */
    private static final ServiceProvider instance = new ServiceProvider();

    /**
     * An object of {@link UserService}
     */
    private final UserService userService = new UserServiceImpl();

    /**
     * An object of {@link OfferService}
     */
    private final OfferService offerService = new OfferServiceImpl();

    /**
     * An object of {@link ReservationService}
     */
    private final ReservationService reservationService = new ReservationServiceImpl();

    /**
     * Private constructor without parameters
     */
    private ServiceProvider(){}

    /**
     * Returns the instance of {@link ServiceProvider}
     * @return {@link ServiceProvider} object
     */
    public static ServiceProvider getInstance(){
        return instance;
    }

    /**
     * Returns the instance of {@link UserService}
     * @return {@link UserService} object
     */
    public UserService getUserService(){
        return userService;
    }

    /**
     * Returns the instance of {@link OfferService}
     * @return {@link OfferService} object
     */
    public OfferService getOfferService(){return offerService;}

    /**
     * Returns the instance of {@link ReservationService}
     * @return {@link ReservationService} object
     */
    public ReservationService getReservationService(){ return reservationService; }
}
