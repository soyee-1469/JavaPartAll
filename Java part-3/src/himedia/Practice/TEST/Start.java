package himedia.Practice.TEST;

public class Start {
    public static void main(String[] args) {
        Member menu = new ManagementImpl();

        while (true) {
            menu.printPricePlan();
            int choice = menu.printMenu();
             switch (choice) {
                case 1:
                  menu.addMember(members);
                    break;
                case 2:
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;

                default:
                    System.out.println("잘 못 선택하셨습니다.");
                    break;
            }
        }

    }


}

