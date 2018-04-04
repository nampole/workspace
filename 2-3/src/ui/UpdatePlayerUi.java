package ui;

import model.Player;
import model.Team;

import org.apache.commons.lang.StringUtils;

import dao.PlayerDao;
import dao.TeamDao;

public class UpdatePlayerUi extends AbstractUi {

	private PlayerDao playerDao;

	private TeamDao teamDao;

	public void setPlayerDao(PlayerDao dao) {
		this.playerDao = dao;
	}

	public void setTeamDao(TeamDao service) {
		this.teamDao = service;
	}

	public void show() {

		Player player = getPlayer();
		if (player == null) {
			return;
		}

		String name = getName(player);
		if (StringUtils.isNotEmpty(name)) {
			player.setName(name);
		}

		Team team = getTeam(player);
		if (team != null) {
			player.setTeam(team);
		}

		this.playerDao.updatePlayer(player);
		System.out.printf("����ID '%s'�� ������, ������ '%s', �� '%s'�� �����մϴ�.%n", player.getId(), player.getName(), player.getTeam().getName());
	}

	protected Player getPlayer() {
		final String playerId = "����ID";
		// �޴� ǥ��
		showMenu(playerId);
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
		// �ֿܼ� �Էµ� ���� ���
		String id = getInputedString();
		// ���ڿ��� �ԷµǾ� �ִ���?
		if (StringUtils.isEmpty(id)) {
			return null;
			// ��������?
		} else if (UiUtils.isNumeric(id, playerId)) {
			// ID�� ������ �˻�
			Player player = this.playerDao.getPlayer(Integer.valueOf(id));

			if (player == null) {
				// �ش��ϴ� ������ �������� �ʴ´�
				System.out.printf("�Է��Ͻ� ����ID�� '%s'�� ������ ���������ʽ��ϴ�.%n", id);
				return getPlayer();
			}
			return player;
		}
		return getPlayer();
	}

	protected String getName(Player player) {
		final String playerName = "������";
		// �޴� ǥ��
		showMenu(playerName);
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ ������� �ʽ��ϴ�.");
		System.out.printf("��������%s%n", player.getName());
		// �ֿܼ� �Էµ� ���� ���
		String name = getInputedString();
		// ���ڿ��� �ԷµǾ� �ִ���?
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		// 128���� �̳�����?
		if (UiUtils.isSmallLength(name, playerName, 128)) {
			return name;
		}
		return getName(player);
	}

	protected Team getTeam(Player player) {
		final String teamId = "��ID";
		// �޴� ǥ��
		showMenu(teamId);
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ ������� �ʽ��ϴ�.");
		System.out.printf("��������%s %s%n", player.getTeam().getId(), player.getTeam().getName());
		// �ֿܼ� �Էµ� ���� ���
		String id = getInputedString();
		// ���ڿ��� �ԷµǾ� �ִ���?
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		// ��������?
		if (UiUtils.isNumeric(id, teamId)) {
			// ID�� ���� �˻�
			Team team = this.teamDao.getTeam(Integer.valueOf(id));
			if (team == null) {
				// �ش��ϴ� ������ �������� �ʴ´�
				System.out.printf("�Է��Ͻ� ������ '%s'�� ���� �������� �ʽ��ϴ�.%n", id);
				return getTeam(player);
			}
			return team;
		}
		return getTeam(player);
	}

	protected void showMenu(String wanted) {
		System.out.println("--------------------");
		System.out.println("������ ��ܡ������� ���š�");
		System.out.println("");
		System.out.printf("%s�� �Է��� �� Enter�� �����ּ���.", wanted);
	}

}
