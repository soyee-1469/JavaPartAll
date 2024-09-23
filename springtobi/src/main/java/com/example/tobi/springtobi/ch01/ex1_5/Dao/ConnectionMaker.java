package com.example.tobi.springtobi.ch01.ex1_5.Dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
  Connection makerNewConnection() throws ClassNotFoundException, SQLException;
}