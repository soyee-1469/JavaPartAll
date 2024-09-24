package com.example.tobi.springtobi.ex_3_5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}