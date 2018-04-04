package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MenuUi extends AbstractUiTemplate {

    private SelectTeamUi selectTeamUi;

    private InsertPlayerUi insertPlayerUi;

    private SelectPlayerUi selectPlayerUi;

    public void setSelectTeamUi(SelectTeamUi selectTeamUi) {
        this.selectTeamUi = selectTeamUi;
    }

    public void setInsertPlayerUi(InsertPlayerUi insertPlayerUi) {
        this.insertPlayerUi = insertPlayerUi;
    }

    public void setSelectPlayerUi(SelectPlayerUi selectPlayerUi) {
        this.selectPlayerUi = selectPlayerUi;
    }

    protected void showMenu() {
        System.out.println("--------------------");
        System.out.println("������ ��ܡ����޴� ��");
        System.out.println("");
        System.out.println("1.���� ");
        System.out.println("2.�� ��� ");
        System.out.println("3.���� �߰� ");
        System.out.println("4.���� ��� ");
        System.out.println("");
        System.out.println("��ȣ�� �Է��� �� Enter Ű�� �����ּ��� .");
    }

    protected int getMaxMenuNumber() {
        return 4;
    }

    protected int getMinMenuNumber() {
        return 1;
    }

    protected void execute(int number) {
        switch (number) {
        case 1:
            // 1.����
            System.out.println("����Ǿ����ϴ� .");
            System.exit(0);
        case 2:
            // 2.�� ���
            this.selectTeamUi.show();
            break;
        case 3:
            // 3.���� �߰�
            this.insertPlayerUi.show();
            break;
        case 4:
            // 4.���� ���
            this.selectPlayerUi.show();
            break;
        }
    }

    public static void main(String[] args) {
        // ������ ���� ���� �б�
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MenuUi menuUi = context.getBean(MenuUi.class);
        while (true) {
            menuUi.show();
        }
    }
}
