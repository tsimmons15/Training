package dev.simmons.exceptions;

public class IncompleteExpenseException extends RuntimeException {
    public IncompleteExpenseException(String fieldName) {
        super(fieldName + " is required but not provided.");
    }
}
