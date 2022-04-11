package dev.simmons.data;

import dev.simmons.entities.Account;
import dev.simmons.utilities.lists.List;

public interface AccountDAO {
    Account createAccount(Account account);

    Account getAccount(int id);

    boolean updateAccount(Account newAccount);
    boolean updateAccounts(Account... accounts);

    boolean deleteAccount(Account account);
    boolean deleteAccount(int id);
}
