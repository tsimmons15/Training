package dev.simmons.data;

import dev.simmons.entities.Account;

public interface AccountDAO {
    Account createAccount(Account account);

    Account getAccount(int id);

    Account updateAccount(Account newAccount);

    Account deleteAccount(Account account);
    Account deleteAccount(int id);
}
