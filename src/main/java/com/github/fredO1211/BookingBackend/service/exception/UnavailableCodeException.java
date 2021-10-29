package com.github.fredO1211.BookingBackend.service.exception;

import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class UnavailableCodeException extends IllegalArgumentException {
    public UnavailableCodeException() {
        super(MessageProvider.UNAVAILABLE_CODE_EXCEPTION_MSG);
    }
}
