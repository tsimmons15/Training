package dev.simmons.data;

import dev.simmons.entities.Account;
import dev.simmons.utilities.connection.PostgresConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresAccountDAO implements AccountDAO {
    @Override
    public Account createAccount(Account account) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            // Logging here
            se.printStackTrace();
        }

        return null;
    }

    @Override
    public Account getAccount(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            // Logging here
            se.printStackTrace();
        }

        return null;
    }

    @Override
    public Account updateAccount(Account newAccount) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            // Logging here
            se.printStackTrace();
        }

        return null;
    }

    @Override
    public Account deleteAccount(Account account) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            // Logging here
            se.printStackTrace();
        }

        return null;
    }

    @Override
    public Account deleteAccount(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            // Logging here
            se.printStackTrace();
        }

        return null;
    }
}
