package com.example.tobi.springtobi;


import com.example.tobi.springtobi.es1_6.Dao.CountingConnectionMaker;
import com.example.tobi.springtobi.es1_6.Dao.CountingDaoFactory;
import com.example.tobi.springtobi.es1_6.Dao.UserDao;
import com.example.tobi.springtobi.es1_6.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class SpringTobiApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        setCharacter();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        dao.get("tobi");
        dao.get("tobi");
        dao.get("tobi");

//        UserDao dao = new DaoFactory().userDao();//*************************************************
        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Conn count : " + connectionMaker.getCounter());

    }
    private static void setCharacter() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
