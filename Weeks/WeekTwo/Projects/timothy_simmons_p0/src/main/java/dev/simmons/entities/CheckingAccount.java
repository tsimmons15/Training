package dev.simmons.entities;

import dev.simmons.utilities.lists.List;

public class CheckingAccount implements Account{
    private int id;
    private double balance;
    private static final AccountType type = AccountType.Checking;
    private List<Client> owners;

    public CheckingAccount() {
        balance = 0;
        id = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    @Override
    public AccountType getType() {
        return type;
    }
}
