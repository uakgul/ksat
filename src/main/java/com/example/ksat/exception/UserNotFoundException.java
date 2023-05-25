package com.example.ksat.exception;

public class UserNotFoundException extends TaskHandlerException {
    public UserNotFoundException(Long userId) {
        super("User not found, userId:" + userId);
    }
}
