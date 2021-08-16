package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Address;
import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.OfferDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OfferDaoImpl implements OfferDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final OfferDaoImpl instance = new OfferDaoImpl();

    private static final String FIND_ALL_OFFERS_SQL = "SELECT offers.offer_id, offers.owner_id, addresses.country, " +
            "addresses.city, addresses.street, addresses.house_number, addresses.apartment_number, offers.price_per_day, " +
            "offers.description, offer_status.status_type " +
            "FROM offers " +
            "JOIN addresses ON offers.address_id = addresses.id " +
            "JOIN offer_status ON offers.status_id = offer_status.id ";
    private static final String FIND_ALL_OFFERS_EXCEPT_USERS_SQL = "SELECT offers.offer_id, offers.owner_id, addresses.country, " +
            "addresses.city, addresses.street, addresses.house_number, addresses.apartment_number, offers.price_per_day, " +
            "offers.description, offer_status.status_type " +
            "FROM offers " +
            "JOIN addresses ON offers.address_id = addresses.id " +
            "JOIN offer_status ON offers.status_id = offer_status.id " +
            "WHERE offers.owner_id <> ? AND offers.status_id = 1";
    private static final String FIND_OFFERS_BY_OWNER_ID = "SELECT offers.offer_id, offers.owner_id, addresses.country, " +
            "addresses.city, addresses.street, addresses.house_number, addresses.apartment_number, offers.price_per_day, " +
            "offers.description, offer_status.status_type " +
            "FROM offers " +
            "JOIN addresses ON offers.address_id = addresses.id " +
            "JOIN offer_status ON offers.status_id = offer_status.id " +
            "WHERE offers.owner_id = ?";
    private static final String FIND_OFFER_BY_ID = "SELECT offers.offer_id, offers.owner_id, addresses.country, " +
            "addresses.city, addresses.street, addresses.house_number, addresses.apartment_number, offers.price_per_day, " +
            "offers.description, offer_status.status_type " +
            "FROM offers " +
            "JOIN addresses ON offers.address_id = addresses.id " +
            "JOIN offer_status ON offers.status_id = offer_status.id " +
            "WHERE offers.offer_id = ?";
    private static final String FIND_PHOTOS_BY_OFFER_ID_SQL = "SELECT url FROM photos WHERE offer_id = ?";
    private static final String CHANGE_OFFER_STATUS_BY_ID = "UPDATE offers SET status_id = ? WHERE offer_id = ?";
    private static final String SELECT_ADDRESS_BY_DATA = "SELECT id FROM addresses WHERE country = ? AND city = ? AND street = ? AND house_number = ? AND apartment_number = ?";
    private static final String SELECT_OFFER_BY_ADDRESS = "SELECT offer_id FROM offers WHERE address_id = ?";
    private static final String INSERT_NEW_ADDRESS_SQL = "INSERT INTO addresses (country, city, street, house_number, apartment_number) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_NEW_OFFER_SQL = "INSERT INTO offers (owner_id, address_id, price_per_day, description, status_id) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_NEW_PHOTO_SQL = "INSERT INTO photos (offer_id, url) VALUES (?, ?)";

    private OfferDaoImpl(){}
    public static OfferDaoImpl getInstance(){
        return instance;
    }
    @Override
    public List<Offer> findOffersByOwnerId(long id) throws DaoException {
        List<Offer> list = new ArrayList<>();
        Connection connection = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(FIND_OFFERS_BY_OWNER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Offer offer = createOffer(resultSet, connection);
                list.add(offer);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in OfferDao.findOffersByOwnerId request", ex);
                }
            }
            throw new DaoException("Unable to handle OfferDao.findOffersByOwnerId request", e);
        }finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle OfferDao.findOffersByOwnerId request");
                }
            }
        }
        return list;
    }

    @Override
    public void changeOfferStatusById(long id, Offer.OfferStatus status) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_OFFER_STATUS_BY_ID)) {
            statement.setInt(ChangeOfferStatusIndex.STATUS, status.getValue());
            statement.setLong(ChangeOfferStatusIndex.OFFER_ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle OfferDao.changeOfferStatusById request", e);
        }
    }

    @Override
    public long addNewOffer(long owner_id, BigInteger pricePerDay, String description, String country, String city, String street, int houseNumber, int apartmentNumber) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        long addressId = 0;
        long offer_id = 0;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement selectAddressByDataStatement = connection.prepareStatement(SELECT_ADDRESS_BY_DATA);
            fillAddressStatement(selectAddressByDataStatement, country, city, street, houseNumber, apartmentNumber);
            resultSet = selectAddressByDataStatement.executeQuery();
            if (resultSet.next()){
                return 0;
            }

            PreparedStatement insertAddressStatement = connection.prepareStatement(INSERT_NEW_ADDRESS_SQL);
            fillAddressStatement(insertAddressStatement, country, city, street, houseNumber, apartmentNumber);
            insertAddressStatement.execute();

            PreparedStatement getAddressByIdStatement = connection.prepareStatement(SELECT_ADDRESS_BY_DATA);
            fillAddressStatement(getAddressByIdStatement, country, city, street, houseNumber, apartmentNumber);
            resultSet = selectAddressByDataStatement.executeQuery();
            if (resultSet.next()){
                addressId = resultSet.getLong(ColumnName.ID);
            }
            PreparedStatement insertOfferStatement = connection.prepareStatement(INSERT_NEW_OFFER_SQL);
            insertOfferStatement.setLong(InsertOfferIndex.OWNER_ID, owner_id);
            insertOfferStatement.setLong(InsertOfferIndex.ADDRESS_ID, addressId);
            insertOfferStatement.setBigDecimal(InsertOfferIndex.PRICE_PER_DAY, new BigDecimal(pricePerDay));
            insertOfferStatement.setString(InsertOfferIndex.DESCRIPTION, description);
            insertOfferStatement.setInt(InsertOfferIndex.STATUS_ID, 1);
            insertOfferStatement.execute();

            PreparedStatement selectOfferId = connection.prepareStatement(SELECT_OFFER_BY_ADDRESS);
            selectOfferId.setLong(1, addressId);
            resultSet = selectOfferId.executeQuery();
            if (resultSet.next()){
                offer_id = resultSet.getLong(ColumnName.OFFER_ID);
            }

            connection.commit();
            return offer_id;
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in OfferDao.addNewOffer request", ex);
                }
            }
            throw new DaoException("Unable to handle OfferDao.addNewOffer request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle OfferDao.addNewOffer request");
                }
            }
        }
    }

    @Override
    public void addPhotosToOfferById(long id, List<String> photos) throws DaoException {
        Connection connection = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            for (String photo : photos) {
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PHOTO_SQL);
                statement.setLong(InsertPhotoIndex.OFFER_ID, id);
                statement.setString(InsertPhotoIndex.URL, photo);
                statement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in OfferDao.addPhotosToOfferById request", ex);
                }
            }
            throw new DaoException("Unable to handle OfferDao.addPhotosToOfferById request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle OfferDao.addPhotosToOfferById request");
                }
            }
        }
    }

    @Override
    public List<Offer> findAllOffersExceptUsers(long user_id) throws DaoException {
        List<Offer> list;
        Connection connection = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(FIND_ALL_OFFERS_EXCEPT_USERS_SQL);
            statement.setLong(1, user_id);
            ResultSet set = statement.executeQuery();
            list = new ArrayList<>();
            while (set.next()){
                Offer offer = createOffer(set, connection);
                list.add(offer);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in OfferDao.findAllOffersExceptUsers request", ex);
                }
            }
            throw new DaoException("Unable to handle OfferDao.findAllOffersExceptUsers request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle OfferDao.findAllOffersExceptUsers request");
                }
            }
        }
        return list;
    }

    @Override
    public boolean add(Offer entity) {
        return false;
    }

    @Override
    public Optional<Offer> findById(long id) throws DaoException {
        Connection connection = null;
        Offer offer = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(FIND_OFFER_BY_ID);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()){
                offer = createOffer(set, connection);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in OfferDao.findById request", ex);
                }
            }
            throw new DaoException("Unable to handle OfferDao.findById request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle OfferDao.findAllOffersExceptUsers request");
                }
            }
        }
        return Optional.ofNullable(offer);
    }

    @Override
    public List<Offer> findAll() throws DaoException {
        List<Offer> list;
        Connection connection = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(FIND_ALL_OFFERS_SQL);
            list = new ArrayList<>();
            while (set.next()){
                Offer offer = createOffer(set, connection);
                list.add(offer);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in OfferDao.findAll request", ex);
                }
            }
            throw new DaoException("Unable to handle OfferDao.findAll request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle OfferDao.findAll request");
                }
            }
        }
        return list;
    }

    @Override
    public Optional<Offer> updateById(long id, Offer entity) {
        return Optional.empty();
    }
    private static  Offer createOffer(ResultSet resultSet, Connection connection) throws SQLException {
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
        PreparedStatement findPhotosStatement = connection.prepareStatement(FIND_PHOTOS_BY_OFFER_ID_SQL);
        findPhotosStatement.setLong(1, offer.getId());
        ResultSet set = findPhotosStatement.executeQuery();
        while (set.next()){
            offer.addPhoto(set.getString(ColumnName.URL));
        }
        return offer;
    }
    private static void fillAddressStatement(PreparedStatement preparedStatement, String country, String city, String street, int houseNumber, int apartmentNumber) throws SQLException {
        preparedStatement.setString(AddressIndex.COUNTRY, country);
        preparedStatement.setString(AddressIndex.CITY, city);
        preparedStatement.setString(AddressIndex.STREET, street);
        preparedStatement.setInt(AddressIndex.HOUSE_NUMBER, houseNumber);
        preparedStatement.setInt(AddressIndex.APARTMENT_NUMBER, apartmentNumber);
    }
    private static class ChangeOfferStatusIndex{
        private static final int STATUS = 1;
        private static final int OFFER_ID = 2;
    }
    private static class AddressIndex{
        private static final int COUNTRY = 1;
        private static final int CITY = 2;
        private static final int STREET = 3;
        private static final int HOUSE_NUMBER = 4;
        private static final int APARTMENT_NUMBER = 5;
    }
    private static class InsertOfferIndex{
        private static final int OWNER_ID = 1;
        private static final int ADDRESS_ID = 2;
        private static final int PRICE_PER_DAY = 3;
        private static final int DESCRIPTION = 4;
        private static final int STATUS_ID = 5;
    }
    private static class InsertPhotoIndex{
        private static final int OFFER_ID = 1;
        private static final int URL = 2;
    }
}
