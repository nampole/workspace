package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ticket;
import dao.TicketDao;

public class TicketDaoImpl implements TicketDao {

	@PersistenceContext
	private EntityManager em;

	public List<Ticket> getBookedTicketList(Integer userId) {
		// 유저ID로 예약되어 있는 티켓과 랭크 정보를 취득
		return em.createQuery(
				" FROM Ticket AS ticket INNER JOIN FETCH ticket.event AS event "
						+ " INNER JOIN FETCH ticket.rank AS rank WHERE ticket.reservation.user.userId = :userId ",
				Ticket.class).setParameter("userId", userId).getResultList();
	}

	public List<Ticket> getNotBookedTicketList(Integer rankId, Integer quantity) {
		return em.createQuery(
				" FROM Ticket AS ticket LEFT OUTER JOIN FETCH ticket.reservation reservation "
						+ " WHERE ticket.rank.rankId = :rankId AND reservation IS NULL ", Ticket.class).setParameter(
				"rankId", rankId).setMaxResults(quantity).getResultList();
	}

}
