package himedia.Practice.AccountBook_old.AccountBook;

public class AccountBookStart {
    public static void main(String[] args) {
        AccountBook book = new AccountBookImpl();
        while (true) {
            int choice = book.printMenu();
            switch (choice) {
                case 1:
                    book.addContent();
                    break;
                case 2:
                    book.selectAll();
                    break;
                case 3:
                    book.selectContent();
                    break;
                case 4:
                    book.deleteAll();
                    break;
                case 5:
                    book.deleteContent();
                    break;
                case 6:
                    System.out.println("종료되었습니다.");
                    return;
                default:
                    System.out.println("잘못누르셨습니다.");
            }
        }
    }
}

