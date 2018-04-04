package ui;

import java.util.List;

import model.Player;

import org.apache.commons.lang.StringUtils;

import dao.PlayerDao;

public class SelectPlayerUi extends AbstractUiTemplate {

	private PlayerDao playerDao;

	private UpdatePlayerUi updatePlayerUi;

	private DeletePlayerUi deletePlayerUi;

	public void setPlayerDao(PlayerDao dao) {
		this.playerDao = dao;
	}

	public void setDeletePlayerUi(DeletePlayerUi ui) {
		this.deletePlayerUi = ui;
	}

	public void setUpdatePlayerUi(UpdatePlayerUi ui) {
		this.updatePlayerUi = ui;
	}

	public void show() {
		//��� ǥ��
		showHeader();
		// �ֿܼ� �Էµ� ���� ���
		Integer teamId = getTeamId();
		// ���� ����� ǥ��
		showPlayerList(this.playerDao.getPlayerList(teamId));
		System.out.println("Enter Ű�� ���� �ּ���.");
		getInputedString();
		super.show();
	}

	protected Integer getTeamId() {
		System.out.println("����� ǥ���ϰ� ���� ���� ID�� �Է��� �� Enter Ű�� �����ּ���.");
		//�ֿܼ� �Էµ� ���� ���
		String teamId = getInputedString();
		//��������?
		if (StringUtils.isNotEmpty(teamId) && StringUtils.isNumeric(teamId)) {
			return Integer.valueOf(teamId);
		}
		return getTeamId();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("������ ��ܡ������� ��ϡ�");
		System.out.println("");
	}

	protected void showMenu() {
		showHeader();
		System.out.println("1.���� ����");
		System.out.println("2.���� ����");
		System.out.println("3.�޴��� ���ư��� ");
		System.out.println("");
		System.out.println("��ȣ�� �Է��� �� Enter Ű�� ���� �ּ��� .");
	}

	protected void showPlayerList(List<Player> playerList) {
		System.out.println("--------------------");
		System.out.println("�� ���� ��� �������� ��ϡ�");
		// ������ �Ѹ��̶� �ִ���?
		if (!playerList.isEmpty()) {
			Player player = playerList.get(0);
			System.out.printf("���� : %s%n", player.getTeam().getName());
		}
		System.out.println("ID    �̸�");
		for (Player player : playerList) {
			// ����ID�� ������ ǥ��
			System.out.printf("%d  %s%n", player.getId(), player.getName());
		}
	}

	protected int getMaxMenuNumber() {
		return 3;
	}

	protected int getMinMenuNumber() {
		return 1;
	}

	protected void execute(int number) {
		switch (number) {
		case 1:
			// 1.���� ����
			updatePlayerUi.show();
			break;
		case 2:
			// 2.���� ����
			deletePlayerUi.show();
			break;
		default:
			return;
		}
	}

}
