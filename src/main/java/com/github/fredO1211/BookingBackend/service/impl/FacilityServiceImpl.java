package com.github.fredO1211.BookingBackend.service.impl;

import com.github.fredO1211.BookingBackend.domain.Facility;
import com.github.fredO1211.BookingBackend.repository.FacilityRepository;
import com.github.fredO1211.BookingBackend.service.FacilityService;
import com.github.fredO1211.BookingBackend.service.Validator;
import com.github.fredO1211.BookingBackend.service.exception.EntityNotFoundException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableNameException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService, Validator<Facility> {
    private final FacilityRepository repository;

    public FacilityServiceImpl(FacilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Facility valid(Facility facility) {
        if (repository.isFacilityExists(facility.getName())) {
            throw new UnavailableNameException();
        }
        return facility;
    }

    @Override
    public Facility save(@Valid Facility facility) {
        return repository.save(valid(facility));
    }

    @Override
    public List<Facility> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Facility> getById(Long id) {
        return repository.findById(id);
    }

    public Facility update(Facility toUpdate, @Valid Facility source) {
        if(!source.getName().equals(toUpdate.getName())){
            valid(source);
            toUpdate.setName(source.getName());
        }
        toUpdate.setBasicRentAmount(source.getBasicRentAmount());
        return repository.save(toUpdate);
    }

    @Override
    public Facility update(Long id, Facility source) {
        Facility toUpdate = repository.findById(id).orElseThrow(()->{
            throw new EntityNotFoundException();
        });
        return update(toUpdate, source);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(this::delete);
    }

    @Override
    public void delete(Facility facility) {
        repository.delete(facility);
    }
}
