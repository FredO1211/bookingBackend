package com.github.fredO1211.BookingBackend.service;

public interface Validator<T> {
    T valid(T t);
}
