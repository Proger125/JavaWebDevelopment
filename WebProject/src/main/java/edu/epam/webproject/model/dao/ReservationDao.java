package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.DaoException;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationDao extends AbstractDao<Reservation>{
    List<Reservation> findReservationsByTenantId(long id) throws DaoException;
    void changeReservationStatusById(long id, Reservation.ReservationStatus status) throws DaoException;
    List<Reservation> findReservationsByOfferId(long id) throws DaoException;
    List<Reservation> findInActiveReservationsByOfferId(long id) throws DaoException;
    void addNewReservation(long tenant_id, long offer_id, Date arrivalDate, Date departureDate, BigInteger totalPrice) throws DaoException;
}
