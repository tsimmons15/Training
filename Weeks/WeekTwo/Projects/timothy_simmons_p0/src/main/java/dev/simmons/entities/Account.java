package dev.simmons.entities;

import dev.simmons.utilities.lists.List;

public interface Account {
    int getId();
    void setId(int id);
    double getBalance();
    void setBalance(double balance);
    boolean deposit(double amount);
    boolean withdraw(double amount);
    List<Client> getOwners();
    void setOwners(List<Client> owners);
    AccountType getType();
    String getDiagnostics();

    static Account accountFactory(String type) {
        Account result = null;
        switch (AccountType.valueOf(type)) {
            case Checking:
                result = new CheckingAccount();
                break;
            case Savings:
                result = new SavingsAccount();
                break;
            case CD:
                result = new CDAccount();
                break;
        }

        return result;
    }

    static Account accountFactory(int ordinal) {
        Account result = null;
        switch (ordinal) {
            case 1:
                result = new CheckingAccount();
                break;
            case 2:
                result = new SavingsAccount();
                break;
            case 3:
                result = new CDAccount();
                break;
        }

        return result;
    }

    enum AccountType {
        Checking(0), Savings(.03), CD(.10);
        public final double interest;
        private AccountType(double interest) {
            this.interest = interest;
        }
    }
}
