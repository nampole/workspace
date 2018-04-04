package ui;

import java.util.List;

import model.Reservation;
import model.Ticket;
import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import dao.ReservationDao;
import dao.TicketDao;
import dao.UserDao;

public class InsertReservationUi extends AbstractUi {

	private TicketDao ticketDao;

	private UserDao userDao;

	private ReservationDao reservationDao;

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	@Transactional
	public void show() {
		// ����� ǥ��
		showHeader();
		// �ֿܼ� �Էµ� ���� ���
		Integer rankId = getNumber();
		if (rankId == null) {
			return;
		}
		// �޴��� ǥ��
		showMenu("Ƽ�� �ż�");
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
		// �ֿܼ� �Էµ� ���� ���
		Integer quantity = getNumber();
		if (quantity == null) {
			return;
		}
		// ��ũ ID�� �����ִ� Ƽ�� �ż��� �˻�
		List<Ticket> ticketList = this.ticketDao.getNotBookedTicketList(rankId, quantity);
		if (ticketList == null) {
			// �ش��ϴ� Ƽ���� �������� ����
			System.out.println("�Էµ� ID�� ��ũ�� Ƽ���� �����ϴ�. �ٽ� �ѹ� �Է����ּ���.");
			show();
			return;
		}

		if (ticketList.size() < quantity.intValue()) {
			// Ƽ���� �Էµ� �ż���ŭ �������� �ʴ�
			System.out.println("Ƽ���� �Էµ� �ż���ŭ �������� �ʽ��ϴ�. �ٽ� �ѹ� �Է��� �ּ���.");
			show();
			return;
		}
		// �޴��� ǥ��
		showMenu("�̸�");
		// �ֿܼ� �Էµ� ���� ���
		String name = getInputedString();
		// ���ڿ��� �ԷµǾ�����?
		if (StringUtils.isBlank(name)) {
			return;
		}
		// ���������� ������ �˻�
		User user = this.userDao.getUser(name);
		if (user == null) {
			// �ű� ������ ����
			user = new User();
			user.setName(name);
			this.userDao.addUser(user);
		}
		// ���� ó���� ����
		reserve(ticketList, user);
	}

	protected void reserve(List<Ticket> ticketList, User user) {
		
		for (Ticket ticket : ticketList) {
			// �ű� ������ ����
			Reservation reservation = new Reservation();
			reservation.setReservationId(ticket.getTicketId());
			reservation.setTicket(ticket);
			reservation.setUser(user);

			// �����ͺ��̽��� ������ ���
			this.reservationDao.addReservation(reservation);
		}
	}

	protected Integer getNumber() {
		// �ֿܼ� �Էµ� ���� ���
		String number = getInputedString();
		// ���ڿ��� �ԷµǾ�����?
		if (StringUtils.isBlank(number)) {
			return null;
		}
		// ��������?
		if (StringUtils.isNumeric(number)) {
			return Integer.valueOf(number);
		}
		System.out.println("���ڷ� �Է����ּ���.");

		return getNumber();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ��Ƽ�ϡ�");
		System.out.println("");
		showMenu("�����ϰ� ���� Ƽ���� ID");
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s�� �Է��� �� Enter�� �����ּ���.%n", wanted);
	}
}
