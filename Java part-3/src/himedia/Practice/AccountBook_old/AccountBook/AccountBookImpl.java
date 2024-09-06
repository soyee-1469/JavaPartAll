package himedia.Practice.AccountBook_old.AccountBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook {
    private int totalMoney;
    // 날짜 내용
    private HashMap<String, List<String>> contentMap;
    // 날짜 - 금액
    private HashMap<String, List<Integer>> moneyMap;
    private HashMap<String, Integer> allMap;

    public AccountBookImpl() {
        this.contentMap = new HashMap<>();
        this.moneyMap = new HashMap<>();
    }


    @Override
    public int printMenu() {
        System.out.println("============================= 가계부 프로그램 ================================");
        System.out.println("[1]내역추가 [2]내역조회(전체) [3]날짜로 조회하기 [4]전체삭제 [5]부분삭제(날짜기준) [6]종료");
        System.out.println("===========================================================================");
        Scanner sc = new Scanner(System.in);
        System.out.println("원하는 번호를 입력하세요.");
        return sc.nextInt();
    }

    @Override
    public void addContent() {
        Scanner sc = new Scanner(System.in);
        String date = getNowDateTime();
        System.out.println("내용을 입력해주세요.");
        String content = sc.nextLine().trim();
        System.out.println("금액을 입력해주세요.");
        int money = sc.nextInt();
        insertDatabase(date, content, money);
        totalMoney += money;


    }

    private String getNowDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().format(formatter);
    }


    @Override
    public void insertDatabase(String date, String content, int money) {

        if (contentMap.containsKey(date)) {

            List<String> list = contentMap.get(date);
            list.add(content);

            List<Integer> list2 = moneyMap.get(date);
            list2.add(money);

            contentMap.put(date, list);
            moneyMap.put(date, list2);

        } else {

            List<String> strings = new ArrayList<>();
            strings.add(content);

            List<Integer> integers = new ArrayList<>();
            integers.add(money);

            contentMap.put(date, strings);
            moneyMap.put(date, integers);

        }

    }


    @Override
    public void selectAll() {
        for (String key : contentMap.keySet()) {
            System.out.println("날짜 :" + key);
            System.out.println("내용 :" + contentMap.get(key));
            System.out.println("금액 :" + moneyMap.get(key) + "원");
            System.out.println("현재 총 금액 : " + totalMoney);
        }
    }

    @Override
    public String selectContent() {

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 항목을 입력하시오.");
        String content1 = sc.nextLine();

        List<String> result = contentMap.get(content1);
        List<Integer> result2 = moneyMap.get(content1);
        System.out.println(result);
        System.out.println(result2);
        return content1;
    }





    @Override
    public void deleteContent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색할 항목을 입력하세요.");
        String date = sc.nextLine().trim();
        contentMap.remove(date);
        System.out.println("삭제되었습니다.");

    }

    @Override
    public void deleteAll() {
       contentMap.clear();
        System.out.println("모두 삭제되었습니다.");
    }


    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}