package ui;

import model.Reservation;

import org.apache.commons.lang.StringUtils;

import dao.ReservationDao;

public class DeleteReservationUi extends AbstractUi {

	private ReservationDao reservationDao;

	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public void show() {
		// ����� ǥ��
		showHeader();
		// �ֿܼ��� �Էµ� ���� ���
		Integer reservationId = getReservationId();
		if (reservationId == null) {
			return;
		}
		// ID�� ������ �˻�
		Reservation reservation = this.reservationDao.getReservation(reservationId);
		if (reservation == null) {
			// �ش��ϴ� ������ �������� �ʴ´�
			System.out.println("�Էµ� ID�� �ش��ϴ� ������ �������� �ʽ��ϴ�");
			show();
		}
		// ������ ���
		this.reservationDao.cancelReservation(reservation);
		System.out.printf("ID�� '%s'�� ������ ����߽��ϴ�.%n", reservationId);
	}

	protected Integer getReservationId() {
		// �ֿܼ��� �Էµ� ���� ���
		String reservationId = getInputedString();
		// ���ڿ��� �ԷµǾ� �ִ���?
		if (StringUtils.isBlank(reservationId)) {
			return null;
		}
		// ��������?
		if (StringUtils.isNumeric(reservationId)) {
			return Integer.valueOf(reservationId);
		}
		System.out.println("ID�� ���ڷ� �Է��� �ּ���.");

		return getReservationId();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ������ ��ҡ�");
		System.out.println("");
		System.out.println("������ ����� Ƽ���� ID�� �Է��� �� Enter�� �����ּ���.");
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
	}
}
