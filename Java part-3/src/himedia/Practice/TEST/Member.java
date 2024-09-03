package himedia.Practice.TEST;

public interface Member {
 int printMenu();
 int printPricePlan();
 void addMember(String[][] members);
 boolean checkEmail(String[][] members, String email);
 void selectEmail(String[][] members);
 void selectName(String[][] members);
 void selectAll(String[][] members);
 void updateMember(String[][] members);
 void deleteMember(String[][] members);
}