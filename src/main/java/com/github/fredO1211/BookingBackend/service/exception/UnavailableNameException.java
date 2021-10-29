package com.github.fredO1211.BookingBackend.service.exception;

import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class UnavailableNameException extends IllegalArgumentException {
    public UnavailableNameException() {
        super(MessageProvider.UNAVAILABLE_NAME_EXCEPTION_MSG);
    }
}
