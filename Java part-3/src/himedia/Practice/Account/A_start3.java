package himedia.Practice.Account;

public class A_start3 {
    public static void main(String[] args) {
        A_account account = new A_accountImpl();
        account.printCreateAccountMenu();
        while (true) {

            int choice = account.printMenu();
            switch (choice) {
                case 1:
                    account.addMoney();
                    break;
                case 2:
                    account.subMoney();
                    break;
                case 3:
                    account.printHistory();
                    break;
                case 4:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
            }
        }

    }
    }

