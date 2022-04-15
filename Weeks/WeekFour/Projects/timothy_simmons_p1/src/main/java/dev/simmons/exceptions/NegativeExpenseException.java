package dev.simmons.exceptions;

public class NegativeExpenseException extends RuntimeException {
    public NegativeExpenseException() {
        super("Attempt to submit a negative expense.");
    }
}
