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
		// 메뉴 표시
		showMenu();
		// 콘솔에 입력된 값을 취득
		String id = getInputedString();
		// 문자열이 입력되어 있는지?
		if (StringUtils.isEmpty(id)) {
			return;
			// 숫자인지?
		} else if (UiUtils.isNumeric(id, "선수ID")) {
			// ID로 선수를 취득
			Player player = this.playerDao.getPlayer(Integer.valueOf(id));
			if (player == null) {
				// 해당하는 선수가 존재하지 않는다
				System.out.printf("입력한 선수ID '%s'인 선수는 존재하지 않습니다.%n", id);
				show();
			} else {
				// 선수를 삭제
				this.playerDao.deletePlayer(player);
				System.out.printf("선수ID '%s'라는 선수를 삭제합니다.%n", id);
			}
		} else {
			show();
		}
	}

	protected void showMenu() {
		System.out.println("--------------------");
		System.out.println("『선수 명단』「선수 삭제」");
		System.out.println("");
		System.out.println("선수ID를 입력한 후 Enter를 눌러주세요.");
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
	}

}
