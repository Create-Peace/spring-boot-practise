package com.ming.practise.common.auth;

public class UserLoginInvalidException extends RuntimeException {

    public UserLoginInvalidException (String message) {
        super(message);
    }
}
