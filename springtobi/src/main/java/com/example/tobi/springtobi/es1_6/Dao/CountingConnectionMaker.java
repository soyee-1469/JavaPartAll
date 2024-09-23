package com.example.tobi.springtobi.es1_6.Dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {

    int counter = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makerNewConnection() throws SQLException, ClassNotFoundException {
       this.counter++;
        return realConnectionMaker.makerNewConnection();
    }

    public int getCounter() {

        return counter;
    }
}
