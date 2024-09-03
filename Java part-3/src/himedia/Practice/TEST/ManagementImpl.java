package himedia.Practice.TEST;

import java.util.Scanner;

public class ManagementImpl implements Member {
    static int totalCnt = 0;
    static int totalMemberCnt = 0;
    @Override
    public int printPricePlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[요금제를 선택하세요.]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명 [4]FreePass : 무제한");
        return sc.nextInt();
    }

    @Override
    public void addMember(String[][] members) {
        if ( totalMemberCnt >= totalCnt ) {
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        // 사용자로부터 이름, 이메일, 연락처
        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력하세요.");
        String email = sc.nextLine();
        System.out.println("연락처를 입력하세요.");
        String phone = sc.nextLine();

        // -> 예외처리2
        // 이메일 체크 필요
        // ->
        if ( checkEmail(members, email) ) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }

        // members에 넣어주세요.
        members[totalMemberCnt][0] = name;
        // 이메일, 연락처...
        members[totalMemberCnt][1] = email;
        members[totalMemberCnt][2] = phone;

        totalMemberCnt++;
    }

    @Override
    public boolean checkEmail(String[][] members, String email) {
        return false;
    }

    @Override
    public void selectEmail(String[][] members) {

    }

    @Override
    public void selectName(String[][] members) {

    }

    @Override
    public void selectAll(String[][] members) {

    }

    @Override
    public void updateMember(String[][] members) {

    }

    @Override
    public void deleteMember(String[][] members) {

    }

    @Override
    public int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");
        System.out.println("메뉴를 선택해주세요");
        return sc.nextInt();
    }

}