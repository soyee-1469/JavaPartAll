package practice.notice2;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Start {
    public static void main(String[] args) {
// 한글깨지는거 방지 소스
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//인터페이스 연결 불러와 선언
        Notice notice = new NoticeImpl();
        while (true) {
            int choice = notice.printMenu();
            switch (choice) {
                case 1:
                    notice.login();
                    break;
                case 2:
                    notice.signup();
                    break;
                case 3:
                    notice.allList();
                    break;
                case 4:
                    notice.upload();
                    break;
                case 5:
                    notice.edit();
                    break;
                case 6:
                    notice.delete();
                    break;
                case 7:
                    notice.logout();
                    break;
                case 8:
                    notice.deleteUser();
                    break;
                case 9:
                    System.out.println("종료되었습니다.");
                    return;
                default:
                    System.out.println("선택하신 메뉴는 없습니다.");
            }
        }


    }
}

