package himedia.Practice.Review;

public interface A_review {
    int printMenu();
    void addReview();
    void insertDatabase(String id, String content, int likeCnt);
    void selectAll();
    void upLike();
    void deleteContent();
    boolean checkString(String str);
}
