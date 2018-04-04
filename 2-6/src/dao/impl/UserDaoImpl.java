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
		// ���������� ������ ���
		List<User> userList = em.createQuery(" FROM User AS user WHERE user.name = :name ", User.class).setParameter(
				"name", name).getResultList();

		if (userList.isEmpty()) {
			return null;
		}

		return userList.get(0);
	}

	public User getUser(Integer id) {
		// ID�� ������ ���
		return em.find(User.class, id);
	}

	public void updateUser(User user) {
		// ������ ����
		em.merge(user);
	}

	public void addUser(User user) {
		em.persist(user);
	}
}
