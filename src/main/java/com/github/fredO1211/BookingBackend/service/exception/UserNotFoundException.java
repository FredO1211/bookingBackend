package com.github.fredO1211.BookingBackend.service.exception;

import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        this(MessageProvider.USER_NOT_FOUND_MSG);
    }

    private UserNotFoundException(String s) {
        super(s);
    }
}
