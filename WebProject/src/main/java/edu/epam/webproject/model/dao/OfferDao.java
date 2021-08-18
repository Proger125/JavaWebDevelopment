package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.math.BigInteger;
import java.util.List;

public interface OfferDao extends AbstractDao<Offer>{
    /**
     * Finds all {@link Offer} by {@link User} id
     * @param id - {@link User} (owner) id
     * @return {@link List<Offer>}
     * @throws DaoException when problems with database connection occurs
     */
    List<Offer> findOffersByOwnerId(long id) throws DaoException;

    /**
     * Changes {@link Offer} status by {@link Offer} id
     * @param id - offer id
     * @param status - new status
     * @throws DaoException when problems with database connection occurs
     */
    void changeOfferStatusById(long id, Offer.OfferStatus status) throws DaoException;

    /**
     * Inserts new {@link Offer} into database
     * @param owner_id - owner id
     * @param pricePerDay - price per day
     * @param description - new offer description
     * @param country - new offer country
     * @param city - new offer country
     * @param street - new offer street
     * @param houseNumber - new offer number of house
     * @param apartmentNumber - new offer number of apartment
     * @return new offer id
     * @throws DaoException when problems with database connection occurs
     */
    long addNewOffer(long owner_id, BigInteger pricePerDay, String description, String country, String city, String street, int houseNumber, int apartmentNumber) throws DaoException;

    /**
     * Adds photos to {@link Offer}
     * @param id - offer id
     * @param photos - {@link List<String>} of photos url
     * @throws DaoException when problems with database connection occurs
     */
    void addPhotosToOfferById(long id, List<String> photos) throws DaoException;

    /**
     * Finds {@link List<Offer>} which do not belong to the {@link User}
     * @param user_id - user id
     * @return {@link List<Offer>}
     * @throws DaoException which do not belong to the user
     */
    List<Offer> findAllOffersExceptUsers(long user_id) throws DaoException;
}
