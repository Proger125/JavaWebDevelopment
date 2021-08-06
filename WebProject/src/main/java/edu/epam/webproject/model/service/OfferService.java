package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> findAllOffers() throws ServiceException;
}
