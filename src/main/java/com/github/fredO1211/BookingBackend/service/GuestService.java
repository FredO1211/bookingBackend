package com.github.fredO1211.BookingBackend.service;

import com.github.fredO1211.BookingBackend.domain.Guest;

import java.util.List;

public interface GuestService extends CrudService<Guest, Long> {
    void delete(Long id);
    List<Guest> getAll();
}
