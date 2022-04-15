package dev.simmons.exceptions;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException() {
        super("Unable to find the resources requested. Check that they exist.");
    }
}
