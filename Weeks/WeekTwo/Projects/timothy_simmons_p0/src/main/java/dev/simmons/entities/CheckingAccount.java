package dev.simmons.entities;

import dev.simmons.utilities.lists.List;

public class CheckingAccount implements Account{
    private int id;
    private double balance;
    public static final double MAXIMUM_TRANSACTION = 500000;
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
    public boolean deposit(double amount) {
        if (amount < 0 || amount > MAXIMUM_TRANSACTION) {
            return false;
        }

        this.balance += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            return false;
        }

        this.balance -= amount;
        return true;
    }

    @Override
    public List<Client> getOwners() {
        return this.owners;
    }

    @Override
    public void setOwners(List<Client> owners) {
        this.owners = owners;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    @Override
    public String getDiagnostics() {
        return "[" + getType().name() + "](id=" + id + ", " + this.balance + ")";
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof CheckingAccount) && this.id == ((CheckingAccount)o).getId();
    }

    @Override
    public String toString() {
        return String.format("%d  $%.2f", this.id, this.balance);
    }
}
