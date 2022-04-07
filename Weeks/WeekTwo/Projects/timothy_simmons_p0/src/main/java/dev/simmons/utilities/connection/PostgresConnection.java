package dev.simmons.utilities.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

    private static final String password = System.getenv("POSTGRES_PASSWORD");
    private static final String username = System.getenv("POSTGRES_USERNAME");
    private static final String urlAWS = "jdbc:postgresql://" + System.getenv("POSTGRES_AWS") + "/bank";
    private static final String urlLocal = "jdbc:postgresql://localhost:5432/bank";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(urlLocal, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
