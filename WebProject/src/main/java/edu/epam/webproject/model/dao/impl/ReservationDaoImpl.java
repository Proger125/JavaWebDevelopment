package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.ReservationDao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final ReservationDaoImpl instance = new ReservationDaoImpl();

    private static final String FIND_ALL_RESERVATIONS_SQL = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservations.arrival_date, reservations.departure_date, reservations.total_price, " +
            "reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id";
    private static final String FIND_RESERVATIONS_BY_TENANT_ID_SQL = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservations.arrival_date, reservations.departure_date, reservations.total_price, " +
            "reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id " +
            "WHERE reservations.tenant_id = ?";
    private static final String FIND_RESERVATIONS_BY_OFFER_ID_SQL = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservations.arrival_date, reservations.departure_date, reservations.total_price, " +
            "reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id " +
            "WHERE reservations.offer_id = ? AND reservations.departure_date > ?";
    private static final String FIND_IN_ACTIVE_RESERVATIONS_BY_OFFER_ID = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservations.arrival_date, reservations.departure_date, reservations.total_price, " +
            "reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id " +
            "WHERE reservations.offer_id = ? AND reservations.status_id = 3";
    private static final String INSERT_NEW_RESERVATION_SQL = "INSERT INTO reservations (tenant_id, offer_id, arrival_date, departure_date, status_id, total_price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_RESERVATION_STATUS_SQL = "UPDATE reservations SET status_id = ? WHERE reservation_id = ?";
    private ReservationDaoImpl(){}

    public static ReservationDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Reservation> findReservationsByTenantId(long id) throws DaoException {
        List<Reservation> list;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_TENANT_ID_SQL)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()){
                Reservation reservation = createReservation(resultSet);
                list.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findReservationsByTenantId request", e);
        }
        return list;
    }

    @Override
    public void changeReservationStatusById(long id, Reservation.ReservationStatus status) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_RESERVATION_STATUS_SQL)) {
            statement.setInt(ChangeReservationStatusIndex.STATUS, status.getValue());
            statement.setLong(ChangeReservationStatusIndex.RESERVATION_ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle ReservationDao.changeReservationStatusById request", e);
        }
    }

    @Override
    public List<Reservation> findReservationsByOfferId(long id) throws DaoException {
        List<Reservation> list = new ArrayList<>();
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_OFFER_ID_SQL)){
            statement.setLong(1, id);
            statement.setDate(2, new Date((new java.util.Date()).getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Reservation reservation = createReservation(resultSet);
                list.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findReservationsByOfferId request", e);
        }
        return list;
    }

    @Override
    public List<Reservation> findInActiveReservationsByOfferId(long id) throws DaoException {
        List<Reservation> list = new ArrayList<>();
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_IN_ACTIVE_RESERVATIONS_BY_OFFER_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Reservation reservation = createReservation(resultSet);
                list.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findInActiveReservationsByOfferId request", e);
        }
        return list;
    }

    @Override
    public void addNewReservation(long tenant_id, long offer_id, java.util.Date arrivalDate, java.util.Date departureDate, BigInteger totalPrice) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_RESERVATION_SQL)){
            statement.setLong(AddNewReservationIndex.TENANT_ID, tenant_id);
            statement.setLong(AddNewReservationIndex.OFFER_ID, offer_id);
            statement.setDate(AddNewReservationIndex.ARRIVAL_DATE, new Date(arrivalDate.getTime()));
            statement.setDate(AddNewReservationIndex.DEPARTURE_DATE, new Date(departureDate.getTime()));
            statement.setInt(AddNewReservationIndex.STATUS_ID, Reservation.ReservationStatus.IN_PROCESSING.getValue());
            statement.setBigDecimal(AddNewReservationIndex.TOTAL_PRICE, new BigDecimal(totalPrice));
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.addNewReservation request", e);
        }
    }

    @Override
    public boolean add(Reservation entity) {
        return false;
    }

    @Override
    public Optional<Reservation> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() throws DaoException {
        List<Reservation> list;
        try(Connection connection = pool.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet set = statement.executeQuery(FIND_ALL_RESERVATIONS_SQL);
            list = new ArrayList<>();
            while (set.next()){
                Reservation reservation = createReservation(set);
                list.add(reservation);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findAll request", e);
        }
        return list;
    }

    @Override
    public Optional<Reservation> updateById(long id, Reservation entity) {
        return Optional.empty();
    }
    private static Reservation createReservation(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong(ColumnName.RESERVATION_ID));
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.TENANT_ID));
        reservation.setTenant(user);
        Offer offer = new Offer();
        offer.setId(resultSet.getLong(ColumnName.OFFER_ID));
        reservation.setOffer(offer);
        reservation.setArrivalDate(resultSet.getDate(ColumnName.ARRIVAL_DATE));
        reservation.setDepartureDate(resultSet.getDate(ColumnName.DEPARTURE_DATE));
        reservation.setTotalPrice(resultSet.getBigDecimal(ColumnName.TOTAL_PRICE).toBigInteger());
        reservation.setStatus(Reservation.ReservationStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE).toUpperCase()));
        return reservation;
    }
    private static class AddNewReservationIndex{
        private static final int TENANT_ID = 1;
        private static final int OFFER_ID = 2;
        private static final int ARRIVAL_DATE = 3;
        private static final int DEPARTURE_DATE = 4;
        private static final int STATUS_ID = 5;
        private static final int TOTAL_PRICE = 6;
    }
    private static class ChangeReservationStatusIndex{
        private static final int STATUS = 1;
        private static final int RESERVATION_ID = 2;
    }
}
