package com.example.tobi.springtobi.es1_5.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makerNewConnection() throws SQLException, ClassNotFoundException {
        String URL = "jdbc:mysql://localhost:3306/spring_tobi";
        String USER = "root";
        String PASSWORD = "1234";

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
