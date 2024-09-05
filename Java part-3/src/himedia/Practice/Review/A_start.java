package himedia.Practice.Review;

public class A_start {
    public static void main(String[] args) {
        A_review rv = new A_reviewImpl();

        while (true) {
            int choice = rv.printMenu();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("종료되었습니다.");
                    return;
            }
        }
    }
}
