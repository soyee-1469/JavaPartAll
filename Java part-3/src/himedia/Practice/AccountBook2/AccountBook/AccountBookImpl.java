package himedia.Practice.AccountBook2.AccountBook;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook {
    HashMap<String, Integer> bookMap;

    public AccountBookImpl() {
        this.bookMap = new HashMap<>();
    }

    @Override
    public void addBook() {
        Scanner sc = new Scanner(System.in);
        //자동날짜입력
        String date = getNowDateTime();
        System.out.println("항목을 입력하세요");
        String book = sc.nextLine();
        System.out.println("금액을 입력하세요");
        int money = sc.nextInt();
        bookMap.put(book, money);
        bookMap.put(date, money);
        System.out.println(date + " - " + book + " - " + money);

    }

    public String getNowDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public void bookAll() {

        String date = bookMap.keySet().toArray()[0].toString();
        bookMap.forEach((book, money) -> System.out.println(date + book + " - " + money));

    }

    @Override
    public void deleteAll() {
        bookMap.clear();
    }

    @Override
    public void deleteBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[삭제]항목을 입력하세요.");
        String book = sc.nextLine();

        if (!checkBook(book)) {
            System.out.println("찾으시는 회원이 없습니다");
            return;
        }

        bookMap.remove(book);
    }

    @Override
    public int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[메뉴를 선택하세요");
        System.out.println("[1]항목추가 [2]전체조회 [3]전체삭제");
        System.out.println("[4]내역삭제 [5]종료");

        return sc.nextInt();
    }


    private boolean checkBook(String book) {
        return bookMap.containsKey(book);
    }
}