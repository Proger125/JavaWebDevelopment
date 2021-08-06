package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDao extends AbstractDao<Reservation>{
    List<Reservation> findReservationsByHirerId(long id);
    boolean changeReservationStatusById(long id);
}
