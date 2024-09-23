package com.example.tobi.springtobi.es1_1.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String URL = "jdbc:mysql://localhost:3306/spring_tobi2";
        String USER = "root";
        String PASSWORD = "1234";

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
