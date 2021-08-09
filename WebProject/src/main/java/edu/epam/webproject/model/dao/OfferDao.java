package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OfferDao extends AbstractDao<Offer>{
    List<Offer> findOffersByOwnerId(long id) throws DaoException;
    boolean changeOfferStateById(long id);
    //FIXME
    Optional<List<Date> > findAllBookedDaysByOfferId(long id);

}
