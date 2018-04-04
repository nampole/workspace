package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	public User getUser(String name) {
		// 유저명으로 유저를 취득
		List<User> userList = em.createQuery(" FROM User AS user WHERE user.name = :name ", User.class).setParameter(
				"name", name).getResultList();

		if (userList.isEmpty()) {
			return null;
		}

		return userList.get(0);
	}

	public User getUser(Integer id) {
		// ID로 유저를 취득
		return em.find(User.class, id);
	}

	public void updateUser(User user) {
		// 유저를 변경
		em.merge(user);
	}

	public void addUser(User user) {
		em.persist(user);
	}
}
