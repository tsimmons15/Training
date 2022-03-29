package Example;

public class CreditCard {

    private int balance;
    private int creditLimit;

    CreditCard() {
        this.balance = 0;
        this.creditLimit = 1000;
    }

    CreditCard(int balance, int creditLimit) {
        setBalance(balance);
        setCreditLimit(creditLimit);
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance >= 0)
            this.balance = balance;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        if (creditLimit >= 0)
            this.creditLimit = creditLimit;
    }

    public void debit(int amount) {
        int newBalance = balance + amount;
        if (newBalance < this.creditLimit) {
            balance = newBalance;
        }
        else {
            System.out.println("Insufficient limit!");
        }
        System.out.println("Balance: " + balance);
    }

    public void credit(int amount) {
        if (amount > 0) {
            balance -= amount;
        }

        System.out.println("Balance: " + balance);
    }

    public void showAccount() {
        System.out.println("=======================");
        System.out.println("+       Account       +");
        System.out.println("=======================");
        System.out.println("\tLimit\t: " + creditLimit + "\n\tBalance\t: " + balance);
    }

    private void alterCreditLimit(int amount) {
        this.creditLimit += amount;
    }
}
