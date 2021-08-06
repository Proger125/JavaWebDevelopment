package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.DaoProvider;
import edu.epam.webproject.model.dao.OfferDao;
import edu.epam.webproject.model.service.OfferService;

import java.util.List;
import java.util.Optional;

public class OfferServiceImpl implements OfferService {
    private static final DaoProvider provider = DaoProvider.getInstance();
    private static final OfferDao offerDao = provider.getOfferDao();
    @Override
    public List<Offer> findAllOffers() throws ServiceException {
        try {
            return offerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllOffers request at OfferService");
        }
    }
}
