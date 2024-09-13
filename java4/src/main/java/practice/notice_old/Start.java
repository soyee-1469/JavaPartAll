package practice.notice_old;

public class Start {


    public static void main(String[] args) {
        Notice notice = new NoticeImpl();
              while (true) {
            int choice = notice.printMenu();
            switch (choice) {
                case 1:
                    notice.login();
                    break;
                case 2:
                    notice.join();
                    break;
                case 3:
                    notice.listAll();
                    break;
                case 4:
                    notice.postUpload();
                    break;
                case 5:
                    notice.postEdit();
                    break;
                case 6:
                    notice.postDelete();
                    break;
                case 7:
                    notice.logout();
                    break;
                case 8:
                    break;
                case 9:
                    System.out.println("logout successful");
                    return;
                default:
                    System.out.println("wrong choice");

            }
        }

    }

}
