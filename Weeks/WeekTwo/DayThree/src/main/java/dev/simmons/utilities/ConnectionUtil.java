package dev.simmons.utilities;

import java.sql.*;

public class ConnectionUtil {
    private static final String password = "drzij4&%4S";
    private static final String username = "postgres";
    private static final String urlLocal = "jdbc:postgresql://localhost:5432/library";
    public static Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection(urlLocal, username, password);
            return connection;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
}