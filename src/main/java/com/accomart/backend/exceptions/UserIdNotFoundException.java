package com.accomart.backend.exceptions;

public class UserIdNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; 

    public UserIdNotFoundException(String message) {
        super(message);
    }
}
