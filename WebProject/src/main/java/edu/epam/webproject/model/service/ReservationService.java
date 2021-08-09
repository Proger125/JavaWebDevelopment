package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Reservation;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAllReservations() throws ServiceException;
    List<Reservation> findReservationsByTenantId(long id) throws ServiceException;
}
