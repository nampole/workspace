package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Reservation;
import dao.ReservationDao;

public class ReservationDaoImpl implements ReservationDao {

	@PersistenceContext
	private EntityManager em;

	public void cancelReservation(Reservation reservation) {
		reservation = em.merge(reservation);
		// 예약을 삭제
		em.remove(reservation);

	}

	public Reservation getReservation(Integer reservationId) {
		// ID로 예약을 취득
		return em.find(Reservation.class, reservationId);
	}

	public void addReservation(Reservation reservation) {
		// 예약을 등록
		em.persist(reservation);
	}

}
