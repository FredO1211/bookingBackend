package com.github.fredO1211.BookingBackend.service.exception;

import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class NoMailsFoundException extends IllegalArgumentException {
    public NoMailsFoundException() {
        super(MessageProvider.NO_MAILS_FOUND_MSG);
    }
}
