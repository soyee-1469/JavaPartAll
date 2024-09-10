package himedia.Practice.AccountBook2.AccountBook;

public class AccountBookStart {
    public static void main(String[] args) {
        AccountBook book = new AccountBookImpl();
        while (true) {
            int choice = book.printMenu();
            switch (choice) {
                case 1:
                    book.addBook();
                    break;
                case 2:
                    book.bookAll();
                    break;
                case 3:
                    book.deleteAll();
                    break;
                case 4:
                    book.deleteBook();
                    break;
                case 5:
                    System.out.println("종료되었습니다.");
                    return;

                default:
                    System.out.println("잘못누르셨습니다.");
            }
        }
    }
}

