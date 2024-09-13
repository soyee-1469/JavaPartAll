package practice.notice2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static practice.notice_old.NoticeImpl.connection;

public class NoticeImpl implements Notice {
    private String userid = null;


    @Override
    //메뉴출력 및 입력 받기
    public int printMenu() {
        System.out.println(userid == null ? "로그인해주세요" : userid);
        System.out.println("----------------메뉴------------------");
        System.out.println("[1]로그인 [2]회원가입 ");
        System.out.println("[3]글목록보기 [4]글등록 [5]글수정 [6]글삭제");
        System.out.println("[7]로그아웃 [8]회원탈퇴 [9]프로그램종료");
        System.out.println("메뉴를 선택해 주세요");
//        Scanner sc = new Scanner(System.in);
//        return sc.nextInt();
//        ↓ 재사용 없을경우 짧게
        return new Scanner(System.in).nextInt();
    }

    @Override
    public void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------회원가입 페이지-------");
        System.out.println("아이디를 입력해주세요");
        String userid = sc.nextLine();
        System.out.println("비밀번호를 입력해주세요");
        String password = sc.nextLine();
        signupDatabase(userid, password);
    }

    //회원가입 정보(userid, password user테이블에 저장하기
    private void signupDatabase(String userid, String password) {
        String query = "INSERT INTO USER (userid,password) VALUES (?,?)";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            System.out.println("userid : " + userid + " password : " + password + "로 생성되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //로그인페이지 정보 받기
    @Override
    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------로그인페이지-------");
        System.out.println("아이디를 입력하세요");
        String userid = sc.nextLine();
        System.out.println("패스워드를 입력하세요");
        int password = sc.nextInt();
        loginDatabase(userid, password);
    }

    //로그인정보 받은거 찾아보기
    private void loginDatabase(String userid, int password) {
        String query = "SELECT * FROM USER WHERE userid = ? AND password = ?";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);

        ) {
            preparedStatement.setString(1, userid);
            preparedStatement.setInt(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            //아이디 여부 먼저 확인
            if (resultSet.next()) {
                String id = resultSet.getString("userid");
                System.out.println("로그인되었습니다." + id);
                this.userid = id;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void upload(){
        Scanner sc = new Scanner(System.in);
        System.out.println("제목을 입력해주세요");
        String subject = sc.nextLine();
        System.out.println("내용을 입력해주세요");
        String content = sc.nextLine();
        uploadDatabase(subject, content);
    }


    private void uploadDatabase(String subject, String content) {
        String query = "INSERT INTO CONTENT (userid,subject,content) VALUES (?,?,?)";
        try (
                Connection conn = connection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
        ) {
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, subject);
            preparedStatement.setString(3, content);
            preparedStatement.executeUpdate();
            System.out.println("게시글이 등록되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //모든 리스트 불러오기 모두공개
    public void allList(){
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

    }




