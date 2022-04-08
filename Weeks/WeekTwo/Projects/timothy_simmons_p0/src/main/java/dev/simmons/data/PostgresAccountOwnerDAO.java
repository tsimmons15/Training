package dev.simmons.data;

import dev.simmons.entities.Account;
import dev.simmons.entities.Client;
import dev.simmons.logging.Logger;
import dev.simmons.utilities.connection.PostgresConnection;
import dev.simmons.utilities.lists.LinkedList;
import dev.simmons.utilities.lists.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresAccountOwnerDAO implements AccountOwnerDAO{
    @Override
    public boolean addOwner(int accountId, int clientId) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "insert into account_owner (account_id, client_id) values (?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, clientId);

            int updated = statement.executeUpdate();

            return updated == 1;
        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addOwner(Account account, Client client) {
        return addOwner(account.getId(), client.getClientId());
    }

    @Override
    public List<Client> getOwners(int accountId) {
        try (Connection conn = PostgresConnection.getConnection()) {
            // My thought process is, there's no reason they'd need to see the passwords/salt at this point...
            String sql = "select client_id, client_name, client_username from client where client.client_id in (" +
                    "select client_id from account_owner where account_id = ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accountId);

            ResultSet rs = statement.executeQuery();

            List<Client> owners = new LinkedList<>();

            while (rs.next()) {
                Client c = new Client();
                c.setClientId(rs.getInt("client_id"));
                c.setClientName(rs.getString("client_name"));
                c.setClientUsername(rs.getString("client_username"));
                owners.add(c);
            }

            return owners;
        } catch (SQLException | NullPointerException ex) {
            Logger.log(Logger.Level.ERROR, ex);
        }

        return null;
    }

    @Override
    public List<Client> getOwners(Account account) {
        return getOwners(account.getId());
    }

    @Override
    public List<Account> getAccounts(int clientId) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "select * from account where account_id in (select account_id from account_owner where client_id = ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, clientId);

            ResultSet rs = statement.executeQuery();

            List<Account> accounts = new LinkedList<Account>();
            while (rs.next()) {
                Account account = Account.accountFactory(rs.getString("account_type"));
                account.setId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("account_balance"));
                accounts.add(account);
            }

            return accounts;
        } catch (SQLException | NullPointerException ex) {
            Logger.log(Logger.Level.ERROR, ex);
        }

        return null;
    }

    @Override
    public List<Account> getAccounts(Client client) {
        return getAccounts(client.getClientId());
    }

    @Override
    public boolean deleteOwner(Account account, Client client) {
        return deleteOwner(account.getId(), client.getClientId());
    }

    @Override
    public boolean deleteOwner(int accountId, int clientId) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "delete from account_owner where account_id = ? and client_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, clientId);

            int updated = statement.executeUpdate();

            return updated == 1;
        } catch (SQLException | NullPointerException ex) {
            Logger.log(Logger.Level.ERROR, ex);
        }

        return false;
    }
}
