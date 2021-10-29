package com.github.fredO1211.BookingBackend.service.exception;


import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class UnavailableDateException extends IllegalArgumentException{
    public UnavailableDateException() {
        super(MessageProvider.UNAVAILABLE_DATE_EXCEPTION_MSG);
    }
}
