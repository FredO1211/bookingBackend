package com.github.fredO1211.BookingBackend.service.exception;

import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class EntityNotFoundException extends IllegalArgumentException {
    public EntityNotFoundException() {
        super(MessageProvider.ID_DOES_NOT_EXIST_MSG);
    }
}
