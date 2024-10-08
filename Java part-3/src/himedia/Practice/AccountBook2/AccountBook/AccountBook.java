package himedia.Practice.AccountBook2.AccountBook;
//* 가계부 1 (collections 사용할 것)
//기능
//AccountBook.java[I], AccountBookImpl.java, Start.java
//1. 가계부 내역추가
//공책 : 1000원
//연필 : 300원
//합계 : 1300원
//2. 내역조회
//-> 과거 날짜 제목들 리스트가 나온다
//2024-09-04
//        2024-09-03
//        2024-09-02
//        2024-09-01
//        -> 제목을 입력하면(2024-09-04) 해당 날짜의 내역들이 나온다
//3. 전체 삭제
//- 제목을 입력한 날짤와 내역을 삭제시킨다.
//4. 내역 삭제
//- 내역들 중 특정 항목을 삭제시킬 수 있다.
//5. 프로그램 종료
public interface AccountBook {
    void addBook();
    void bookAll() ;
    void deleteAll();
    void deleteBook();
    int printMenu();


}
