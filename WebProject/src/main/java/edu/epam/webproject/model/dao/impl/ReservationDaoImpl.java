package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.ReservationDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final ReservationDaoImpl instance = new ReservationDaoImpl();

    private static final String FIND_ALL_RESERVATIONS_SQL = "SELECT reservations.reservation_id, reservations.tenant_id, " +
            "reservations.offer_id, reservation.arrival_date, reservation.departure_date, reservation.total_price, " +
            "reservation.comment, reservation_status.status_type FROM reservations " +
            "JOIN reservation_status ON reservations.status_id = reservation_status.id";
    private ReservationDaoImpl(){}

    public static ReservationDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Reservation> findReservationsByHirerId(long id) {
        return null;
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
                Reservation reservation = new Reservation();
                reservation.setId(set.getLong(ColumnName.RESERVATION_ID));
                User user = new User();
                user.setId(set.getLong(ColumnName.TENANT_ID));
                reservation.setTenant(user);
                Offer offer = new Offer();
                offer.setId(set.getLong(ColumnName.OFFER_ID));
                reservation.setOffer(offer);
                reservation.setArrivalDate(set.getDate(ColumnName.ARRIVAL_DATE));
                reservation.setDepartureDate(set.getDate(ColumnName.DEPARTURE_DATE));
                reservation.setTotalPrice(set.getBigDecimal(ColumnName.TOTAL_PRICE).toBigInteger());
                reservation.setComment(set.getString(ColumnName.COMMENT));
                reservation.setStatus(Reservation.ReservationStatus.valueOf(set.getString(ColumnName.STATUS_TYPE.toUpperCase())));
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
}
