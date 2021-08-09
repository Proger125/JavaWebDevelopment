package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.ReservationDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final ReservationDaoImpl instance = new ReservationDaoImpl();

    private static final String FIND_ALL_RESERVATIONS_SQL = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservations.arrival_date, reservations.departure_date, reservations.total_price, " +
            "reservations.comment, reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id";
    private static final String FIND_RESERVATIONS_BY_TENANT_ID_SQL = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservations.arrival_date, reservations.departure_date, reservations.total_price, " +
            "reservations.comment, reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id " +
            "WHERE reservations.tenant_id = ?";
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
            throw new DaoException("Unable to handle UserDao findAll request");
        }
        return list;
    }

    @Override
    public boolean changeReservationStatusById(long id) {
        return false;
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
            throw new DaoException("Unable to handle UserDao findAll request");
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
        reservation.setComment(resultSet.getString(ColumnName.COMMENT));
        reservation.setStatus(Reservation.ReservationStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE.toUpperCase())));
        return reservation;
    }
}
