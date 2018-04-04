package dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import model.Event;

import dao.EventDao;

public class EventDaoImpl implements EventDao {

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<Event> getEventList(Date start, Date end) {
		EntityManager em = this.emf.createEntityManager();
		// 範囲指定でイベントを検索
		return em.createQuery(" FROM Event AS event WHERE event.date >= :startDate AND event.date <= :endDate ",
				Event.class).setParameter("startDate", start).setParameter("endDate", end).getResultList();

	}
}