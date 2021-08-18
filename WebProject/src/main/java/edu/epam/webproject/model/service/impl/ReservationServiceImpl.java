package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Offer;
import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.DaoProvider;
import edu.epam.webproject.model.dao.OfferDao;
import edu.epam.webproject.model.dao.ReservationDao;
import edu.epam.webproject.model.service.ReservationService;
import edu.epam.webproject.util.DateUtil;

import java.math.BigInteger;
import java.util.*;

public class ReservationServiceImpl implements ReservationService {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final ReservationDao reservationDao = provider.getReservationDao();
    private final OfferDao offerDao = provider.getOfferDao();
    @Override
    public List<Reservation> findAllReservations() throws ServiceException {
        try {
            List<Reservation> list = reservationDao.findAll();
            for (Reservation reservation : list){
                setOffer(reservation);
            }
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllReservations request at ReservationService", e);
        }
    }

    @Override
    public List<Reservation> findReservationsByTenantId(long id) throws ServiceException {
        try {
            List<Reservation> list =  reservationDao.findReservationsByTenantId(id);
            for (Reservation reservation : list){
                setOffer(reservation);
            }
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findReservationsByTenantId request at ReservationService", e);
        }
    }

    @Override
    public List<Reservation> findInActiveReservationsByOfferId(long id) throws ServiceException {
        try{
            List<Reservation> list =  reservationDao.findInActiveReservationsByOfferId(id);
            for (Reservation reservation : list){
                setOffer(reservation);
            }
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findInActiveReservationsByOfferId request at ReservationService", e);
        }
    }

    @Override
    public List<Date> findAllBookedDaysByOfferId(long id) throws ServiceException {
        try{
            List<Reservation> reservationList = reservationDao.findReservationsByOfferId(id);
            List<Date> dateList = new ArrayList<>();
            for (Reservation reservation : reservationList){
                Date arrivalDate = reservation.getArrivalDate();
                Date departureDate = reservation.getDepartureDate();
                arrivalDate = DateUtil.addDays(arrivalDate, 0);
                for (Date date = arrivalDate; date.before(departureDate) || date.equals(departureDate); date = DateUtil.addDays(date, 1)){
                    dateList.add(date);
                }
            }
            Collections.sort(dateList);
            return dateList;
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllBookedDaysByOfferId request at ReservationService", e);
        }
    }

    @Override
    public void changeReservationStatusById(long id, Reservation.ReservationStatus status) throws ServiceException {
        try{
            reservationDao.changeReservationStatusById(id, status);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle changeReservationStatusById request at ReservationService", e);
        }
    }

    @Override
    public void addNewReservation(long tenant_id, long offer_id, Date arrivalDate, Date departureDate, BigInteger pricePerDay) throws ServiceException {
        Date date = arrivalDate;
        int counter = 0;
        while (date.before(departureDate)){
            date = DateUtil.addDays(date, 1);
            counter++;
        }
        try{
            BigInteger totalPrice = pricePerDay.multiply(new BigInteger(String.valueOf(counter)));
            reservationDao.addNewReservation(tenant_id, offer_id, arrivalDate, departureDate, totalPrice);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle addNewReservation request at ReservationService", e);
        }
    }
    private void setOffer(Reservation reservation) throws DaoException {
        long offer_id = reservation.getOffer().getId();
        Optional<Offer> optionalOffer = offerDao.findById(offer_id);
        Offer offer = null;
        if (optionalOffer.isPresent()){
            offer = optionalOffer.get();
        }
        reservation.setOffer(offer);
    }
}
