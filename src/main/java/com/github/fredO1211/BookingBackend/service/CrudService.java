package com.github.fredO1211.BookingBackend.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, S> {
    T save(T t);
    List<T> getAll(Pageable pageable);
    Optional<T> getById(S id);
    T update(S id, T source);
    void delete(T t);
}
