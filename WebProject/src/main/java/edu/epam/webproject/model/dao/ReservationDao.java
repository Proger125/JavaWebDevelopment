package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ReservationDao extends AbstractDao<Reservation>{
    List<Reservation> findReservationsByTenantId(long id) throws DaoException;
    boolean changeReservationStatusById(long id);
}
