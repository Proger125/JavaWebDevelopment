package edu.epam.webproject.model.service;

import edu.epam.webproject.model.service.impl.OfferServiceImpl;
import edu.epam.webproject.model.service.impl.ReservationServiceImpl;
import edu.epam.webproject.model.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private final OfferService offerService = new OfferServiceImpl();
    private final ReservationService reservationService = new ReservationServiceImpl();

    private ServiceProvider(){}

    public static ServiceProvider getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }

    public OfferService getOfferService(){return offerService;}
    public ReservationService getReservationService(){ return reservationService; }
}
