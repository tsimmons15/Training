package dev.simmons.service;

import dev.simmons.entities.Account;
import dev.simmons.entities.Client;
import dev.simmons.utilities.lists.List;

public interface Bank {
    boolean deposit(Account account, double amount);
    boolean withdraw(Account account, double amount);
    boolean transfer(Account from, Account to, double amount);

    List<Account> getAccountInfo(int clientId);
    List<Client> getOwners(int accountId);
    Client getClient(int clientId);
    Client lookupClient(Client client);
    Client lookupClient(String username);
    Account getAccount(int accountId);
    List<Account> getFullAccountInfo(int clientId);
    List<Account> getFullAccountInfo();

    boolean registerClient(Client client);
    boolean createAccount(Account account, Client owner);
    boolean addOwner(Account account, Client owner);
    boolean removeOwner(Account account, Client owner);

    boolean closeAccount(Account account);
    boolean closeClient(Client client);
}
