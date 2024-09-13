package practice.notice_old;

public interface Notice {
    int printMenu();
    void login();
    void logout();
    void join();
    void listAll();
    void postUpload();
    void postDelete();
    void postDelete(String userid);
    void postEdit();
    void postEdit(int num);

}
