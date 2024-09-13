package practice;

import java.sql.*;
import java.util.Scanner;

public class A_jdbc {
    /*
    1.user id
     password
    name
    age
    phone


    2.로그인
    id
    password
    있으면 "ㅇㅇㅇ welcome! + name + age + phone
    없으면 " nothing

    3.종료

    테이블 생성 members
    id, pk auto


    */


    public static Connection connection() {
        String url = "jdbc:mysql://localhost:3306/java_basic";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conn Success!");

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------MENU-----------");
        System.out.println("[1]JOIN [2]LOGIN [3] LOGOUT");

        return sc.nextInt();
    }

    public static void join() {
        Scanner sc = new Scanner(System.in);
        System.out.println("userid");
        String id = sc.next();
        System.out.println("password");
        int password = sc.nextInt();
        System.out.println("name");
        String name = sc.next();
        System.out.println("age");
        int age = sc.nextInt();
        System.out.println("phone");
        String phone = sc.next();
        insertDate(id, password, name, age, phone);

    }

    public static void insertDate(String userid, int password, String name, int age, String phone) {
        String query = "INSERT INTO member(userid,password,name,age,phone) VALUES(?,?,?,?,?)";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {

            preparedStatement.setString(1, userid);
            preparedStatement.setInt(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, phone);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Insert Success!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("userid");
        String id = sc.next();
        System.out.println("password");
        int password = sc.nextInt();
        selectOne(id, password);
    }

    public static void selectOne(String userid, int password) {
        String query = "SELECT * FROM member WHERE userid = ? AND password = ?";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userid);
            preparedStatement.setInt(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                System.out.println(name + " " + age + " " + phone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        while (true) {
            int choice = printMenu();
            switch (choice) {
                case 1:
                    join();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("logout successful");
                    return;
                default:
                    System.out.println("wrong choice");
            }
        }
    }
}
