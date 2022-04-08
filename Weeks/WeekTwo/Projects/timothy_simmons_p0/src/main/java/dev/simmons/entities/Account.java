package dev.simmons.entities;

public interface Account {
    int getId();
    void setId(int id);
    double getBalance();
    void setBalance(double balance);
    void deposit(double amount);
    boolean withdraw(double amount);
    AccountType getType();
    String displayAccount();

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

    enum AccountType {
        Checking(0), Savings(.03), CD(.10);
        public final double interest;
        private AccountType(double interest) {
            this.interest = interest;
        }
    }
}
