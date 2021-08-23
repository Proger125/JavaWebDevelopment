package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;

import java.math.BigInteger;
import java.util.List;

/**
 * Interface provides methods to interact with {@link Offer} data from DAO layer
 */
public interface OfferService {
    /**
     * Finds all {@link Offer}
     * @return {@link List<Offer>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<Offer> findAllOffers() throws ServiceException;

    /**
     * Finds all {@link Offer} except offers which belong {@link User} with current id
     * @param user_id - user id
     * @return {@link List<Offer>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<Offer> findAllOffersExceptUsers(long user_id) throws ServiceException;

    /**
     * Finds all {@link Offer} by owner id
     * @param id - owner id
     * @return {@link List<Offer>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<Offer> findOffersByOwnerId(long id) throws ServiceException;

    /**
     * Finds {@link Offer} by id
     * @param id - offer id
     * @return {@link Offer}
     * @throws ServiceException when problems with DAO occurs
     */
    Offer findOfferById(long id) throws ServiceException;

    /**
     * Changes {@link Offer} status by id
     * @param id - offer id
     * @param status - new offer status
     * @throws ServiceException when problems with DAO occurs
     */
    void changeOfferStatusById(long id, Offer.OfferStatus status) throws ServiceException;

    /**
     * Adds new {@link Offer}
     * @param owner_id - new offer owner id
     * @param pricePerDay - new offer price per day
     * @param description - new offer description
     * @param country - new offer address field - country
     * @param city - new offer address field - city
     * @param street - new offer address field - street
     * @param houseNumber - new offer address field - number of house
     * @param apartmentNumber - new offer address field - number of apartment
     * @return {@link Long} - new offer id
     * @throws ServiceException when problems with DAO occurs
     */
    long addNewOffer(long owner_id, BigInteger pricePerDay, String description, String country, String city, String street, int houseNumber, int apartmentNumber) throws ServiceException;

    /**
     * Adds photos to {@link Offer}
     * @param id - offer id
     * @param photos - {@link List<String>} - photos url list
     * @throws ServiceException when problems with DAO occurs
     */
    void addPhotosToOfferById(long id, List<String> photos) throws ServiceException;
}
