package himedia.Practice.AccountBook;

public interface AccountBook {
    //1.내역추가
    int printMenu();//입력받기,메뉴출력

    int addMoney();//메뉴생성

    String addSubject();

    void searchHistory();
    void manageHistory(String message);

    //2.내역조회[전체] 과거 날짜 제목들 리스트 2024-09-04 / 공책 / 1000원
    void printHistory();
    //3.내역조회[제목]

    //4.전체삭제
    void deleteHistory();
    //5.내역삭제 [제목]

    //6.프로그램 종료
}
