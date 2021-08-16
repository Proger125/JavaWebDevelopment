package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.exception.ServiceException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> findAllOffers() throws ServiceException;
    List<Offer> findAllOffersExceptUsers(long user_id) throws ServiceException;
    List<Offer> findOffersByOwnerId(long id) throws ServiceException;
    Offer findOfferById(long id) throws ServiceException;
    void changeOfferStatusById(long id, Offer.OfferStatus status) throws ServiceException;
    long addNewOffer(long owner_id, BigInteger pricePerDay, String description, String country, String city, String street, int houseNumber, int apartmentNumber) throws ServiceException;
    void addPhotosToOfferById(long id, List<String> photos) throws ServiceException;
}
