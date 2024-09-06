package himedia.Practice.AccountBook2.AccountBook;

public interface AccountBook {

    int printMenu();//메뉴
    void addContent();;//추가
    void insertDatabase(String date, String content, int money); //데이터넣기
    void selectAll(); //내역조회
    void deleteContent(); //삭제
    void deleteAll(); //삭제
    String selectContent();
}
