package dev.simmons.data;

import dev.simmons.entities.Account;
import dev.simmons.entities.Client;
import dev.simmons.utilities.lists.List;

public interface AccountOwnerDAO {
    boolean addOwner(int accountId, int clientId);
    boolean addOwner(Account account, Client client);

    List<Client> getOwners(int accountId);
    List<Client> getOwners(Account account);

    List<Account> getAccounts(int clientId);
    List<Account> getAccounts(Client client);

    List<Account> getAccountsSolelyOwned(int clientId);
    List<Account> getAccountsSolelyOwned(Client client);

    // Update doesn't necessarily make sense in this case... They can be added to/removed from accounts

    boolean deleteOwner(Account account, Client client);
    boolean deleteOwner(int accountId, int clientId);
}
