package dev.simmons.utilities;

import java.sql.*;

public class ConnectionUtil {
    private static final String password = System.getenv("POSTGRES_PASSWORD");
    private static final String username = System.getenv("POSTGRES_USERNAME");
    private static final String urlAWS = "jdbc:postgresql://" + System.getenv("POSTGRES_AWS") + "/library";
    private static final String urlLocal = "jdbc:postgresql://localhost:5432/library";
    public static Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection(urlLocal, username, password);
            return connection;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception ex) {

        }
        return null;
    }
}