package himedia.Practice.AccountBook;

public class AccountBookStart {
    public static void main(String[] args) {
        AccountBook book = new AccountBookImpl();
        while (true) {
            int choice = book.printMenu();
            switch (choice) {
                case 1:
                    book.addSubject();
                    book.addMoney();
                    break;
                case 2:
                    book.printHistory();
                    break;
                case 3:
                    book.searchHistory();
                    break;
                case 4:book.deleteHistory();
                    break;
                case 5:

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

