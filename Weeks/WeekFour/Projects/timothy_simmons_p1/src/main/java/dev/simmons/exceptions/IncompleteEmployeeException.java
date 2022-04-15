package dev.simmons.exceptions;

public class IncompleteEmployeeException extends RuntimeException{
    public IncompleteEmployeeException(String fieldName) {
        super(fieldName + " is required but not provided.");
    }
}
