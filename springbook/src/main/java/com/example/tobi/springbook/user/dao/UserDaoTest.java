package com.example.tobi.springbook.user.dao;

import com.example.tobi.springbook.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        setCharacter();
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        ConnectionMaker connectionMaker = new DConnectionMaker();
//      UserDao dao = new UserDao(connectionMaker);
//        UserDao dao = new DaoFactory().userDao();
        User user = new User();
        user.setId("user2");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록성공");
        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user2.getId()+"조회성공");

        if(!user.getName().equals(user2.getName())) {
            System.out.println("테스트실패");
            }
        else if(!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패(password)");
        }
        else {
            System.out.println("조회 테스트 성공");
        }

    }
    private static void setCharacter() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
