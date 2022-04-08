package dev.simmons.data;

import dev.simmons.entities.Client;
import dev.simmons.utilities.connection.PostgresConnection;

import java.sql.*;
import java.util.Base64;

public class PostgresClientDAO implements ClientDAO{
    @Override
    public Client createClient(Client client) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "insert into client (client_name, client_username, " +
                    "client_password, client_salt) values (?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getClientName());
            statement.setString(2, client.getClientUsername());
            statement.setString(3, client.getClientPassword());
            statement.setString(4, Base64.getEncoder().encodeToString(client.getClientSalt()));

            int updated = statement.executeUpdate();
            if (updated != 1) {
                return null;
            }

            int id = 0;
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            client.setClientId(id);

            return client;
        } catch (SQLException se) {
            // Implement logging here.
            // Problem with the SQL?
            se.printStackTrace();
        } catch (NullPointerException npe) {
            // Implement logging here
            // Something was null (possibly the Prepared statement)
            npe.printStackTrace();
        }

        return null;
    }

    @Override
    public Client getClient(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "select * from client where client_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs == null) {
                return null;
            }

            Client client = new Client();
            rs.next();
            client.setClientId(rs.getInt("client_id"));
            client.setClientName(rs.getString("client_name"));
            client.setClientPassword(rs.getString("client_password"));
            client.setClientSalt(rs.getString("client_salt").getBytes());

            return client;
        } catch (SQLException se) {
            // Implement logging here.
            // Problem with the SQL?
        } catch (NullPointerException npe) {
            // Implement logging here
            // Something was null (possibly the Prepared statement)
        }

        return null;
    }

    @Override
    public Client getClient(String username) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "select * from client where client_username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if (rs == null) {
                return null;
            }

            Client client = new Client();
            rs.next();
            client.setClientId(rs.getInt("client_id"));
            client.setClientName(rs.getString("client_name"));
            client.setClientPassword(rs.getString("client_password"));
            client.setClientSalt(rs.getBytes("client_salt"));

            return client;
        } catch (SQLException se) {
            // Implement logging here.
            // Problem with the SQL?
        } catch (NullPointerException npe) {
            // Implement logging here
            // Something was null (possibly the Prepared statement)
        }

        return null;
    }

    @Override
    public boolean updateClient(Client newClient) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "update client set client_name = ?, client_username = ?, client_password = ?, client_salt = ? where client_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newClient.getClientName());
            statement.setString(2, newClient.getClientUsername());
            statement.setString(3, newClient.getClientPassword());
            statement.setString(4, Base64.getEncoder().encodeToString(newClient.getClientSalt()));
            statement.setInt(5, newClient.getClientId());

            int updated = statement.executeUpdate();

            return updated == 1;
        } catch (SQLException se) {
            // Implement logging here.
            // Problem with the SQL?
        } catch (NullPointerException npe) {
            // Implement logging here
            // Something was null (possibly the Prepared statement)
        }

        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        return deleteClient(client.getClientId());
    }

    @Override
    public boolean deleteClient(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "delete from client where client_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int updated = statement.executeUpdate();
            if (updated == 1) {
                return true;
            }

            return false;
        } catch (SQLException se) {
            // Implement logging here.
            // Problem with the SQL?
        } catch (NullPointerException npe) {
            // Implement logging here
            // Something was null (possibly the Prepared statement)
        }

        return false;
    }
}
