package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.DaoException;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * The interface provides methods to interact with {@link Reservation} data from database
 */
public interface ReservationDao extends AbstractDao<Reservation>{
    /**
     * Finds all {@link Reservation} of user by reservation id
     * @param id - reservation id
     * @return {@link List<Reservation>} list of reservations
     * @throws DaoException when problems with database connection occurs
     */
    List<Reservation> findReservationsByTenantId(long id) throws DaoException;

    /**
     * Changes {@link Reservation} status by reservation id
     * @param id - reservation id
     * @param status - new reservation status
     * @throws DaoException when problems with database connection occurs
     */
    void changeReservationStatusById(long id, Reservation.ReservationStatus status) throws DaoException;

    /**
     * Finds all {@link Reservation} of specific offer by offer id
     * @param id - offer id
     * @return {@link List<Reservation>} list of reservations
     * @throws DaoException when problems with database connection occurs
     */
    List<Reservation> findReservationsByOfferId(long id) throws DaoException;

    /**
     * Finds all {@link Reservation} with status {@link Reservation.ReservationStatus#IN_PROCESSING} by user id
     * @param id - user id
     * @return {@link List<Reservation>} list of reservations
     * @throws DaoException when problems with database connection occurs
     */
    List<Reservation> findInActiveReservationsByOfferId(long id) throws DaoException;

    /**
     * Inserts new {@link Reservation} into database
     * @param tenant_id - id of user that booked current offer
     * @param offer_id - current offer
     * @param arrivalDate - arrival date
     * @param departureDate - departure date
     * @param totalPrice - total price
     * @throws DaoException when problems with database connection occurs
     */
    void addNewReservation(long tenant_id, long offer_id, Date arrivalDate, Date departureDate, BigInteger totalPrice) throws DaoException;
}
