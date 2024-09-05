package himedia.Practice.Review;

import java.util.Scanner;

public class A_reviewImpl implements A_review {
    @Override
    public int printMenu() {
        System.out.println("==================== 리뷰 프로그램 ====================");
        System.out.println("[1]리뷰 추가 [2]좋아요 [3]전체 보기 [4]리뷰 삭제 [5]종료");
        System.out.println("=====================================================");

        Scanner sc = new Scanner(System.in);
        System.out.println("원하는 번호를 입력하세요.");

        return sc.nextInt();
    }

    @Override
    public void addReview() {

    }

    @Override
    public void insertDatabase(String id, String content, int likeCnt) {

    }

    @Override
    public void selectAll() {

    }

    @Override
    public void upLike() {

    }

    @Override
    public void deleteContent() {

    }

    @Override
    public boolean checkString(String str) {
        return false;
    }
}
