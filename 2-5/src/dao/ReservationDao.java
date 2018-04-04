package dao;

import model.Reservation;

public interface ReservationDao {
    
    void cancelReservation(Reservation reservation);
    
    Reservation getReservation(Integer reservationId);
    
}
