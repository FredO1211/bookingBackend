package com.github.fredO1211.BookingBackend.service;

import com.github.fredO1211.BookingBackend.domain.Facility;

public interface FacilityService extends CrudService<Facility, Long> {
    void delete(Long id);
}
