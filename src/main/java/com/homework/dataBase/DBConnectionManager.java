package com.homework.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private Connection connection;

    public DBConnectionManager(String dbURL, String user, String password)
                                                    throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public Connection getConnection(){
        return connection;
    }
}
