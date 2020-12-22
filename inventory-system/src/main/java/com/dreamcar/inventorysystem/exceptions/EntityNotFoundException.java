package com.dreamcar.inventorysystem.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public static final String EXCEPTION_MESSAGE_FORMAT = "Not found entity %s with id %d";
    public EntityNotFoundException(String message) {
        super(message);
    }
}
