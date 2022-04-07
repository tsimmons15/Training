package dev.simmons.entities;

import dev.simmons.utilities.lists.List;

public interface Account {
    double getBalance();
    void setBalance(double balance);
    void deposit(double amount);
    boolean withdraw(double amount);
    AccountType getType();
    List<Client> getOwners();
    boolean addOwner(Client client);
    boolean addOwners(Client... clients);

    static Account accountFactory(String type) {
        Account result = null;
        switch (AccountType.valueOf(type)) {
            case Checking:
                //result = new CheckingAccount();
                break;
            case Savings:
                //result = new SavingsAccount();
                break;
            case CD:
                //result = new CDAccont();
                break;
        }

        return result;
    }

    enum AccountType {
        Checking, Savings, CD
    }
}
