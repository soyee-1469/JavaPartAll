package com.example.tobi.springtobi.ch03.ex_3_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DeleteAllStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("delete from user");

        return ps;
    }
}