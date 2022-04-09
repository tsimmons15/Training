package dev.simmons.service;

import dev.simmons.entities.Account;
import dev.simmons.entities.Client;
import dev.simmons.utilities.lists.List;

public class Banking implements Bank{
    @Override
    public boolean deposit(Account account, double amount) {
        return false;
    }

    @Override
    public boolean withdraw(Account account, double amount) {
        return false;
    }

    @Override
    public boolean transfer(Account from, Account to, double amount) {
        return false;
    }

    @Override
    public List<Account> getAccountInfo(int clientId) {
        return null;
    }

    @Override
    public List<Client> getOwners(int accountId) {
        return null;
    }

    @Override
    public Client getClient(int clientId) {
        return null;
    }

    @Override
    public Client lookupClient(Client client) {
        return null;
    }

    @Override
    public Account getAccount(int accountId) {
        return null;
    }

    @Override
    public boolean registerClient(Client client) {
        return false;
    }

    @Override
    public boolean createAccount(Account account, Client owner) {
        return false;
    }

    @Override
    public boolean addOwner(Account account, Client owner) {
        return false;
    }

    @Override
    public boolean removeOwner(Account account, Client owner) {
        return false;
    }

    @Override
    public boolean closeAccount(Account account) {
        return false;
    }

    @Override
    public boolean closeClient(Client client) {
        return false;
    }
}
