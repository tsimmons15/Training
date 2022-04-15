package dev.simmons.entities;

public class Expense {
    private int id;
    private long amount;
    private Status status;
    private long date;
    private int issuer;

    public Expense() {
    }

    public Expense(int id, long amount, Status status, long date, int issuer) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.issuer = issuer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getIssuer() {
        return issuer;
    }

    public void setIssuer(int issuer) {
        this.issuer = issuer;
    }

    public enum Status {
      PENDING, APPROVED, DENIED
    };
}
