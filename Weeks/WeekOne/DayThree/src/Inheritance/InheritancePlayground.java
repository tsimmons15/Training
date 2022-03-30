package Inheritance;

import java.util.Objects;

public class InheritancePlayground {
    public static void main(String[] args) {
        AccountHolder holder = new AccountHolder("Adam", "Ranieri");
        CreditCard c = new CreditCard(0, 5000, holder);
        TravelCard t = new TravelCard(0, 100000, holder);
        c.debit(200);
        c.credit(50);
        c.showAccount();
        t.showAccount();
        t.debit(500);
        t.credit(100);
        t.showAccount();

        CreditCard t2 = t;
        // t.getMiles();
        ((TravelCard)t2).getMiles();
        t.getMiles();
    }
}

class CreditCard {
    protected double balance;
    protected int creditLimit;
    protected AccountHolder owner;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public AccountHolder getOwner() {
        return owner;
    }

    public void setOwner(AccountHolder owner) {
        this.owner = owner;
    }

    public CreditCard(double balance, int creditLimit, AccountHolder owner) {
        setBalance(balance);
        setCreditLimit(creditLimit);
        setOwner(owner);
    }

    public void debit(double amount) {
        double newBalance = balance + amount;
        if (newBalance < this.creditLimit) {
            balance = newBalance;
        }
        else {
            System.out.println("Insufficient limit!");
        }
        //System.out.println("Balance: " + balance);
    }

    public void credit(double amount) {
        if (amount > 0) {
            balance -= amount;
        }

        //System.out.println("Balance: " + balance);
    }

    public void showAccount() {
        int idLength = 21 - owner.accountID.length();
        String id = String.format("%" + idLength + "s", owner.accountID);
        System.out.println("=======================");
        System.out.println("+   Credit  Account   +");
        System.out.println("+  Owner: " + id + " +");
        System.out.println("=======================");
        System.out.println("\tLimit\t: $" + this.creditLimit);
        System.out.println("\tBalance\t: $" + this.balance);
    }

    @Override
    public String toString() {
        return "I am a Credit Card";
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, creditLimit, owner);
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}

class TravelCard extends CreditCard {

    private int miles;

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    TravelCard(double balance, int creditLimit, AccountHolder owner) {
        super(balance, creditLimit, owner);
        setMiles(0);
    }

    TravelCard(double balance, int creditLimit, AccountHolder owner, int miles) {
        super(balance, creditLimit, owner);
        setMiles(miles);
    }

    @Override
    public void showAccount() {
        int idLength = 21 - owner.accountID.length();
        String id = String.format("%" + idLength + "s", owner.accountID);
        System.out.println("=======================");
        System.out.println("+   Travel  Account   +");
        System.out.println("+  Owner: " + id + " +");
        System.out.println("=======================");
        System.out.println("\tLimit\t: $" + this.creditLimit);
        System.out.println("\tBalance\t: $" + this.balance);
        System.out.println("\tMiles\t: " + this.miles);
    }

    @Override
    public void debit(double amount) {
        double newBalance = balance + amount;
        if (newBalance < this.creditLimit) {
            this.balance = newBalance;
            this.miles += (amount * .01);
        }
        else {
            System.out.println("Insufficient limit!");
        }
    }

    @Override
    public String toString() {
        return "I am a travel card";
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, creditLimit, owner, miles);
    }
}

class AccountHolder {
    String firstName;
    String lastName;
    String accountID;

    public AccountHolder(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        setAccountID((firstName+lastName).hashCode()+"");
    }

    public AccountHolder() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + accountID;
    }
}
