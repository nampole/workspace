package ui;

import model.Player;

import org.apache.commons.lang.StringUtils;

import dao.PlayerDao;

public class DeletePlayerUi extends AbstractUi {

	private PlayerDao playerDao;

	public void setPlayerDao(PlayerDao dao) {
		this.playerDao = dao;
	}

	public void show() {
		// �޴� ǥ��
		showMenu();
		// �ֿܼ� �Էµ� ���� ���
		String id = getInputedString();
		// ���ڿ��� �ԷµǾ� �ִ���?
		if (StringUtils.isEmpty(id)) {
			return;
			// ��������?
		} else if (UiUtils.isNumeric(id, "����ID")) {
			// ID�� ������ ���
			Player player = this.playerDao.getPlayer(Integer.valueOf(id));
			if (player == null) {
				// �ش��ϴ� ������ �������� �ʴ´�
				System.out.printf("�Է��� ����ID '%s'�� ������ �������� �ʽ��ϴ�.%n", id);
				show();
			} else {
				// ������ ����
				this.playerDao.deletePlayer(player);
				System.out.printf("����ID '%s'��� ������ �����մϴ�.%n", id);
			}
		} else {
			show();
		}
	}

	protected void showMenu() {
		System.out.println("--------------------");
		System.out.println("������ ��ܡ������� ������");
		System.out.println("");
		System.out.println("����ID�� �Է��� �� Enter�� �����ּ���.");
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
	}

}
