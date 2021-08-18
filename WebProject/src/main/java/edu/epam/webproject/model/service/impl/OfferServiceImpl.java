package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.DaoProvider;
import edu.epam.webproject.model.dao.OfferDao;
import edu.epam.webproject.model.service.OfferService;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class OfferServiceImpl implements OfferService {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final OfferDao offerDao = provider.getOfferDao();
    @Override
    public List<Offer> findAllOffers() throws ServiceException {
        try {
            return offerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllOffers request at OfferService");
        }
    }

    @Override
    public List<Offer> findAllOffersExceptUsers(long user_id) throws ServiceException {
        try{
            return offerDao.findAllOffersExceptUsers(user_id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllOffersExceptUsers at OfferService");
        }
    }

    @Override
    public List<Offer> findOffersByOwnerId(long id) throws ServiceException {
        try{
            return offerDao.findOffersByOwnerId(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findOffersByOwnerId request at OfferService");
        }
    }

    @Override
    public Offer findOfferById(long id) throws ServiceException {
        try{
            Optional<Offer> optionalOffer = offerDao.findById(id);
            if (optionalOffer.isPresent()){
                return optionalOffer.get();
            }else{
                throw new ServiceException("Can't find offer by id");
            }
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findOfferById request at OfferService");
        }
    }

    @Override
    public void changeOfferStatusById(long id, Offer.OfferStatus status) throws ServiceException {
        try {
            offerDao.changeOfferStatusById(id, status);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle changeOfferStatusById request at OfferService");
        }
    }

    @Override
    public long addNewOffer(long owner_id, BigInteger pricePerDay, String description, String country, String city, String street, int houseNumber, int apartmentNumber) throws ServiceException {
        try {
            return offerDao.addNewOffer(owner_id, pricePerDay, description, country, city, street, houseNumber, apartmentNumber);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle addNewOffer request at OfferService");
        }
    }

    @Override
    public void addPhotosToOfferById(long id, List<String> photos) throws ServiceException {
        try {
            offerDao.addPhotosToOfferById(id, photos);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle addPhotosToOfferById request at Offer Service");
        }
    }
}
