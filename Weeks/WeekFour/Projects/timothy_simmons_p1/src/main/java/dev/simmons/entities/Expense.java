package dev.simmons.entities;

import java.text.DecimalFormat;
import java.util.Objects;

public class Expense implements Comparable<Expense>{
    private int id;
    private long amount;
    private Status status;
    private long date;
    private int issuer;

    public Expense() {
        this.status = Status.PENDING;
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

    public double getAmount() {
        return (double)(amount/100.0);
    }

    public void setAmount(double amount) {
        this.amount = (long)(amount*100);
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

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "Expense(" + getId() + ") for $" + df.format(getAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expense)) {return false;}
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.amount, this.status, this.date, this.issuer);
    }

    @Override
    public int compareTo(Expense other) {
        return Integer.compare(this.id, other.getId());
    }

    public enum Status {
      PENDING, APPROVED, DENIED
    };
}
