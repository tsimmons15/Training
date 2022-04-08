package dev.simmons.data;

import dev.simmons.entities.Account;

public interface AccountDAO {
    Account createAccount(Account account);

    Account getAccount(int id);

    boolean updateAccount(Account newAccount);

    boolean deleteAccount(Account account);
    boolean deleteAccount(int id);
}
