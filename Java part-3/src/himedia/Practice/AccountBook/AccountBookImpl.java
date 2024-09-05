package himedia.Practice.AccountBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountBookImpl implements AccountBook {
    private int totalMoney=0;

    private List<String> histories;
    private List<String> histories2;
    public AccountBookImpl() {
        histories = new ArrayList<>();
        histories2 = new ArrayList<>();

    }


    @Override
    public int printMenu() {
        System.out.println("==================== 가계부 프로그램 ====================");
        System.out.println("[1]내역추가 [2]내역조회(전체) [3]내역조회(제목) [4]전체삭제 [5]삭제(제목) [6]종료");
        System.out.println("=====================================================");

        System.out.println("현재금액 - " + totalMoney + "원");
        Scanner sc = new Scanner(System.in);
        System.out.println("원하는 번호를 입력하세요.");
        return sc.nextInt();
    }


    public String addSubject(){
        Scanner sc = new Scanner(System.in);
        System.out.println("제목을 입력해주세요");
        String subject = sc.nextLine();
        String historyMessage2= subject;
        manageHistory2(historyMessage2);
        return subject;
    }

    @Override
    public void searchHistory() {

    }
    public void deleteHistory(){
    }

    public void manageHistory2(String message) {
        histories2.add(message);

    }

    @Override
    public void manageHistory(String message) {
        histories.add(getNowDateTime() + " - " +message);

    }

    @Override
    public void printHistory() {
        System.out.println("======== 내역조회 ========");
        histories.sort(Comparator.naturalOrder());
        for ( String h : histories ) {
            for(String h2 : histories2) {
                System.out.println(h2 + ": " + h);
            }
        }
    }

    @Override
    public int addMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("금액을 입력하세요.(+,-)");
        int getMoney = sc.nextInt();
        totalMoney += getMoney;
        // 히스토리 추가
        String historyMessage= "[금액]" + getMoney + "원 ";
        manageHistory(historyMessage);
        return getMoney;
    }



    private String getNowDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);

    }
}
