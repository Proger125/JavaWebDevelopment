package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.ServiceException;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAllReservations() throws ServiceException;
    List<Reservation> findReservationsByTenantId(long id) throws ServiceException;
    List<Reservation> findInActiveReservationsByOfferId(long id) throws ServiceException;
    List<Date> findAllBookedDaysByOfferId(long id) throws ServiceException;
    void changeReservationStatusById(long id, Reservation.ReservationStatus status) throws ServiceException;
    void addNewReservation(long tenant_id, long offer_id, Date arrivalDate, Date departureDate, BigInteger pricePerDay) throws ServiceException;
}
