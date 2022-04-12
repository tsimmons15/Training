package dev.simmons.utilities.connection;

import dev.simmons.utilities.logging.Logger;

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
            return DriverManager.getConnection(urlAWS, username, password);
        } catch (SQLException e) {
            Logger.log(Logger.Level.WARNING, e);
            return null;
        }
    }
}
