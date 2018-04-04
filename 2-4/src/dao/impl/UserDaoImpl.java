package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import model.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao {

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public User getUser(String name) {
		EntityManager em = this.emf.createEntityManager();
		// 유저명으로 유저를 취득
		List<User> userList = em.createQuery(" FROM User AS user WHERE user.name = :name ", User.class).setParameter(
				"name", name).getResultList();

		if (userList.isEmpty()) {
			return null;
		}

		return userList.get(0);
	}
}
