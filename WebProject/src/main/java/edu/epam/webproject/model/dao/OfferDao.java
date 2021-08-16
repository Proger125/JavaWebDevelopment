package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.exception.DaoException;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OfferDao extends AbstractDao<Offer>{
    List<Offer> findOffersByOwnerId(long id) throws DaoException;
    void changeOfferStatusById(long id, Offer.OfferStatus status) throws DaoException;
    long addNewOffer(long owner_id, BigInteger pricePerDay, String description, String country, String city, String street, int houseNumber, int apartmentNumber) throws DaoException;
    void addPhotosToOfferById(long id, List<String> photos) throws DaoException;
    List<Offer> findAllOffersExceptUsers(long user_id) throws DaoException;
}
