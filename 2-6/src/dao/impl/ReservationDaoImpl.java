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
		// ������ ����
		em.remove(reservation);

	}

	public Reservation getReservation(Integer reservationId) {
		// ID�� ������ ���
		return em.find(Reservation.class, reservationId);
	}

	public void addReservation(Reservation reservation) {
		// ������ ���
		em.persist(reservation);
	}

}
