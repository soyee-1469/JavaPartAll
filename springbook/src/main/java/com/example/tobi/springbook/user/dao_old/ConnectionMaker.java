package com.example.tobi.springbook.user.dao_old;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    Connection makeConnection() throws ClassNotFoundException, SQLException;


}
