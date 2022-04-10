package dev.simmons.entities;

import dev.simmons.utilities.lists.List;

public class CDAccount implements Account{
    private int id;
    private double balance;
    public static final double MAXIMUM_TRANSACTION = 500000;
    private static final AccountType type = AccountType.CD;
    private List<Client> owners;

    public CDAccount() {
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
    public boolean deposit(double amount) {
        if (amount < 0 || amount > MAXIMUM_TRANSACTION) {
            return false;
        }

        this.balance += amount * (1.00 + type.interest);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            return false;
        }

        this.balance -= amount * (1.00 + type.interest * 1.5);
        return true;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof CDAccount) && this.id == ((CDAccount)o).getId();
    }

    @Override
    public String toString() {
        return String.format("%d  $%.2f", this.id, this.balance);
    }
}
