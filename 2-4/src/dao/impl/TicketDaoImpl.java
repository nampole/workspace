package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import model.Ticket;
import dao.TicketDao;

public class TicketDaoImpl implements TicketDao {

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<Ticket> getBookedTicketList(Integer userId) {
		EntityManager em = this.emf.createEntityManager();
		// 유저ID로 예약되어 있는 티켓과 랭크 정보를 취득
		return em.createQuery(
				" FROM Ticket AS ticket INNER JOIN FETCH ticket.event AS event "
						+ " INNER JOIN FETCH ticket.rank AS rank WHERE ticket.reservation.user.userId = :userId ",
				Ticket.class).setParameter("userId", userId).getResultList();
	}

}
