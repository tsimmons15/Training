package dev.simmons.utilities.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements DataConnection {

    private static final String url = "jdbc:postgresql://localhost:5432";
    private static final String username = "postgres";
    private static final String password = "drzij4&%4S";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
