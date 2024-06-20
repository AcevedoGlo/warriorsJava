package com.globant.granmaRestaurant.exception;

public class ComboNotFoundException extends RuntimeException {
    public ComboNotFoundException(String message) {
        super(message);
    }
}