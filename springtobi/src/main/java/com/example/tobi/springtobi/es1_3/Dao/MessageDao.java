package com.example.tobi.springtobi.es1_3.Dao;

import com.example.tobi.springtobi.es1_3.Dao.ConnectionMaker;
import com.example.tobi.springtobi.es1_3.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDao {
    private ConnectionMaker connectionMaker;

    //    private SimpleConnectionMaker simpleConnectionMaker;
    public MessageDao(ConnectionMaker connectionMaker) {
//        simpleConnectionMaker = new SimpleConnectionMaker();
//        connectionMaker = new DConnectionMaker();
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makerNewConnection();
        PreparedStatement ps = conn.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makerNewConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;


    }
}
