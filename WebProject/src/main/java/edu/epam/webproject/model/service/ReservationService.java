package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.ServiceException;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Interface provides methods to interact with {@link Reservation} data from DAO layer
 */
public interface ReservationService {
    /**
     * Finds all {@link Reservation}
     * @return {@link List<Reservation>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<Reservation> findAllReservations() throws ServiceException;

    /**
     * Finds all {@link Reservation} by tenant id
     * @param id - tenant id
     * @return {@link List<Reservation>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<Reservation> findReservationsByTenantId(long id) throws ServiceException;

    /**
     * Finds all {@link Reservation} with {@link Reservation.ReservationStatus#IN_PROCESSING} by offer id
     * @param id - offer id
     * @return {@link List<Reservation>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<Reservation> findInActiveReservationsByOfferId(long id) throws ServiceException;

    /**
     * Finds all booked days of {@link Offer} by id
     * @param id - offer id
     * @return {@link List<Date>} - all booked dates
     * @throws ServiceException when problems with DAO occurs
     */
    List<Date> findAllBookedDaysByOfferId(long id) throws ServiceException;

    /**
     * Changes {@link Reservation} status by id
     * @param id - reservation id
     * @param status - new reservation status
     * @throws ServiceException when problems with DAO occurs
     */
    void changeReservationStatusById(long id, Reservation.ReservationStatus status) throws ServiceException;

    /**
     * Adds new {@link Reservation}
     * @param tenant_id - new reservation tenant id
     * @param offer_id - new reservation offer id
     * @param arrivalDate - new reservation arrival date
     * @param departureDate - new reservation departure date
     * @param pricePerDay - new reservation price per day
     * @throws ServiceException when problems with DAO occurs
     */
    void addNewReservation(long tenant_id, long offer_id, Date arrivalDate, Date departureDate, BigInteger pricePerDay) throws ServiceException;
}
