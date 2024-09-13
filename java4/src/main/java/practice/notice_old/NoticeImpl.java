package practice.notice_old;

import java.sql.*;
import java.util.Scanner;

public class NoticeImpl implements Notice {
    private String userid = null;

    public static Connection connection() {
        String url = "jdbc:mysql://localhost:3306/notice";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertDate(String userid, int password) {
        String query = "INSERT INTO USER(userid,password) VALUES(?,?)";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            preparedStatement.setInt(2, password);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Insert Success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("|-----------------------------MENU---------------------------|");
        System.out.println(userid == null ? "" : userid + " Welcome!");
        System.out.println(" [1]LOGIN [2]JOIN");
        System.out.println(" [3]LIST ALL [4]POST UPLOAD  [5]POST EDIT  [6]POST DELETE");
        System.out.println(" [7]LOGOUT  [8]QUIT  [9]END");
        System.out.println(" MENU CHOICE ?");
        return scanner.nextInt();

    }

    @Override
    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("userid");
        String userid = sc.next();
        System.out.println("password");
        int password = sc.nextInt();
        selectOne(userid, password);

    }

    public void selectOne(String userid, int password) {
        String query = "SELECT userid,password FROM USER WHERE userid = ? AND password = ?";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userid);
            preparedStatement.setInt(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("userid");
                int pass = resultSet.getInt("password");

                System.out.println("Welcome " + id + "!!!!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    @Override
    public void logout() {
        userid = null;
        System.out.println("LOGOUT SUCCESS");
    }

    @Override
    public void join() {
        Scanner sc = new Scanner(System.in);
        System.out.println("userid");
        String userid = sc.next();
        System.out.println("password");
        int password = sc.nextInt();
        insertDate(userid, password);
    }


    @Override
    public void listAll() {
        String query = "select num,userid,subject,content FROM CONTENT";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);

        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int num = rs.getInt("num");
                String userid = rs.getString("userid");
                String subject = rs.getString("subject");
                String content = rs.getString("content");
                System.out.println("[" + num + "]" + userid + "subject :" + subject + " - " + "content :" + content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void postUpload() {
        Scanner sc = new Scanner(System.in);
        System.out.println("SUBJECT");
        String subject = sc.nextLine();
        System.out.println("content");
        String content = sc.nextLine();
        insertDate(userid, subject, content);

    }

    public static void insertDate(String userid, String subject, String content) {
        String query = "INSERT INTO CONTENT(userid,subject,content) VALUES(?,?,?)";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, subject);
            preparedStatement.setString(3, content);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("PostUpload Success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void postDelete() {
        if (userid == null) {
            System.out.println("Need to login first!");
            return;
        }
        String query = "select num,userid,subject,content FROM CONTENT where userid = ?";

        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int num = rs.getInt("num");
                String userid = rs.getString("userid");
                String subject = rs.getString("subject");
                String content = rs.getString("content");
                System.out.println("[" + num + "]" + userid + "subject :" + subject + " - " + "content :" + content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        postDelete(userid);
    }

    @Override
    public void postDelete(String userid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete num");
        int num = sc.nextInt();

        String query = "DELETE FROM CONTENT WHERE num = ?";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, num);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Delete Success!");
            }
            System.out.println("Delete Fail!");
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public void postEdit() {

        if (userid == null) {
            System.out.println("Need to login first!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("NUMBER");
        int num = sc.nextInt();
        postEdit(num);
    }

    @Override
    public void postEdit(int num) {
        Scanner sc = new Scanner(System.in);
        String query = "UPDATE CONTENT SET subject = ?,content = ? WHERE num = ?"; //? 값 순서대로 index 작성
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            System.out.println("SUBJECT");
            String subject = sc.nextLine();
            System.out.println("CONTENT");
            String content = sc.nextLine();
            preparedStatement.setString(1, subject); // ?첫번째
            preparedStatement.setString(2, content); // ?두번쨰
            preparedStatement.setInt(3, num); // ? 세번째
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("update Success!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
