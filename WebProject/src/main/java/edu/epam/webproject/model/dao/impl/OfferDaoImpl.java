package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Address;
import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.OfferDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OfferDaoImpl implements OfferDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final OfferDaoImpl instance = new OfferDaoImpl();

    private static final String FIND_ALL_OFFERS_SQL = "SELECT offers.offer_id, offers.owner_id, addresses.country, " +
            "addresses.city, addresses.street, addresses.house_number, addresses.apartment_number, offers.price_per_day, " +
            "offers.description, offer_status.status_type " +
            "FROM offers " +
            "JOIN addresses ON offers.address_id = addresses.id " +
            "JOIN offer_status ON offers.status_id = offer_status.id ";
    private static final String FIND_OFFERS_BY_OWNER_ID = "SELECT offers.offer_id, offers.owner_id, addresses.country, " +
            "addresses.city, addresses.street, addresses.house_number, addresses.apartment_number, offers.price_per_day, " +
            "offers.description, offer_status.status_type " +
            "FROM offers " +
            "JOIN addresses ON offers.address_id = addresses.id " +
            "JOIN offer_status ON offers.status_id = offer_status.id " +
            "WHERE offers.owner_id = ?";

    private OfferDaoImpl(){}
    public static OfferDaoImpl getInstance(){
        return instance;
    }
    @Override
    public List<Offer> findOffersByOwnerId(long id) throws DaoException {
        List<Offer> list;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_OFFERS_BY_OWNER_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()){
                Offer offer = createOffer(resultSet);
                list.add(offer);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle OfferDao.findOffersByOwnerId request", e);
        }
        return list;
    }

    @Override
    public boolean changeOfferStateById(long id) {
        return false;
    }

    @Override
    public Optional<List<Date>> findAllBookedDaysByOfferId(long id) {
        return Optional.empty();
    }

    @Override
    public boolean add(Offer entity) {
        return false;
    }

    @Override
    public Optional<Offer> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Offer> findAll() throws DaoException {
        List<Offer> list;
        try(Connection connection = pool.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet set = statement.executeQuery(FIND_ALL_OFFERS_SQL);
            list = new ArrayList<>();
            while (set.next()){
                Offer offer = createOffer(set);
                list.add(offer);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle OfferDao.findAll request");
        }
        return list;
    }

    @Override
    public Optional<Offer> updateById(long id, Offer entity) {
        return Optional.empty();
    }
    private static  Offer createOffer(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setId(resultSet.getLong(ColumnName.OFFER_ID));
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.OWNER_ID));
        offer.setOwner(user);
        offer.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        offer.setPricePerDay(resultSet.getBigDecimal(ColumnName.PRICE_PER_DAY).toBigInteger());
        offer.setStatus(Offer.OfferStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE).toUpperCase()));
        Address address = new Address();
        address.setCountry(resultSet.getString(ColumnName.COUNTRY));
        address.setCity(resultSet.getString(ColumnName.CITY));
        address.setStreet(resultSet.getString(ColumnName.STREET));
        address.setHouseNumber(resultSet.getInt(ColumnName.HOUSE_NUMBER));
        address.setApartmentNumber(resultSet.getInt(ColumnName.APARTMENT_NUMBER));
        address.setApartmentNumber(resultSet.getInt(ColumnName.APARTMENT_NUMBER));
        offer.setAddress(address);
        return offer;
    }
}
