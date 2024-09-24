package com.example.tobi.springtobi.ex_3_5.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    void setParameters(PreparedStatement ps) throws SQLException;
}
