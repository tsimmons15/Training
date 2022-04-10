package dev.simmons.service;

import dev.simmons.data.*;
import dev.simmons.entities.Account;
import dev.simmons.entities.Client;
import dev.simmons.utilities.lists.List;

public class Banking implements Bank{
    private ClientDAO clientDAO;
    private AccountDAO accountDAO;
    private AccountOwnerDAO aoDAO;

    public Banking() {
        clientDAO = new PostgresClientDAO();
        accountDAO = new PostgresAccountDAO();
        aoDAO = new PostgresAccountOwnerDAO();
    }

    @Override
    public boolean deposit(Account account, double amount) {
        if (account == null) {
            return false;
        }

        boolean deposited = account.deposit(amount);
        if (!deposited){
            return false;
        }
        return accountDAO.updateAccount(account);
    }

    @Override
    public boolean withdraw(Account account, double amount) {
        if (account == null) {
            return false;
        }

        boolean withdrawn = account.withdraw(amount);
        if (!withdrawn) {
            return false;
        }
        return accountDAO.updateAccount(account);
    }

    @Override
    public boolean transfer(Account from, Account to, double amount) {
        if (from == null || to == null) {
            return false;
        }

        boolean result = from.withdraw(amount);
        result = result & to.deposit(amount);
        if (!result) {
            return false;
        }

        return accountDAO.updateAccounts(from, to);
    }

    @Override
    public List<Account> getAccountInfo(int clientId) {
        return aoDAO.getAccounts(clientId);
    }

    @Override
    public List<Client> getOwners(int accountId) {
        return aoDAO.getOwners(accountId);
    }

    @Override
    public Client getClient(int clientId) {
        return clientDAO.getClient(clientId);
    }

    @Override
    public Client lookupClient(Client client) {
        if (client == null || client.getClientUsername().length() > 0) {
            return null;
        }

        return clientDAO.getClient(client.getClientUsername());
    }

    @Override
    public Account getAccount(int accountId) {
        return accountDAO.getAccount(accountId);
    }

    @Override
    public boolean registerClient(Client client) {
        if (client == null) {
            return false;
        }
        return clientDAO.createClient(client) != null;
    }

    @Override
    public boolean createAccount(Account account, Client owner) {
        if (account == null || owner == null) {
            return false;
        }
        account = accountDAO.createAccount(account);
        return aoDAO.addOwner(account, owner);
    }

    @Override
    public boolean addOwner(Account account, Client owner) {
        if (account == null || owner == null) {
            return false;
        }
        return aoDAO.addOwner(account, owner);
    }

    @Override
    public boolean removeOwner(Account account, Client owner) {
        if (account == null || owner == null) {
            return false;
        }

        boolean result = aoDAO.deleteOwner(account, owner);
        List<Client> owners = aoDAO.getOwners(account);
        if (owners.length() > 0) {
            return result;
        }

        return result & closeAccount(account);
    }

    @Override
    public boolean closeAccount(Account account) {
        return accountDAO.deleteAccount(account);
    }

    @Override
    public boolean closeClient(Client client) {
        if (client == null) {
            return false;
        }

        List<Account> accounts = aoDAO.getAccountsSolelyOwned(client);
        boolean result = true;
        for(int i = 0; i < accounts.length(); i++) {
            result &= removeOwner(accounts.get(i), client);
        }

        result &= clientDAO.deleteClient(client);

        return result;
    }
}