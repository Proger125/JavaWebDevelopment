package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.DaoProvider;
import edu.epam.webproject.model.dao.ReservationDao;
import edu.epam.webproject.model.service.ReservationService;

import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {
    private static final DaoProvider provider = DaoProvider.getInstance();
    private static final ReservationDao reservationDao = provider.getReservationDao();
    @Override
    public List<Reservation> findAllReservations() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllReservations request at Reservation service");
        }
    }
}
