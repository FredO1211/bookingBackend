package com.github.fredO1211.BookingBackend.service.impl;

import com.github.fredO1211.BookingBackend.domain.Guest;
import com.github.fredO1211.BookingBackend.repository.GuestRepository;
import com.github.fredO1211.BookingBackend.service.GuestService;
import com.github.fredO1211.BookingBackend.service.dto.GuestDTO;
import com.github.fredO1211.BookingBackend.service.exception.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository repository;

    public GuestServiceImpl(GuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Guest save(@Valid Guest guest) {
        return repository.save(guest);
    }

    @Override
    public List<Guest> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Guest> getById(Long id) {
        return repository.findById(id);
    }

    public List<Guest> getByProps(GuestDTO guest){
        return repository.findGuestByProps(
                guest.getName(),
                guest.getPhoneNumber(),
                guest.getEmail());
    }

    public Guest update(Guest toUpdate, @Valid Guest source) {
        toUpdate.setName(source.getName());
        toUpdate.setEmail(source.getEmail());
        toUpdate.setPhoneNumber(source.getPhoneNumber());
        toUpdate.setAdditionalInformation(source.getAdditionalInformation());
        return repository.save(toUpdate);
    }

    @Override
    public Guest update(Long id, Guest source) {
        Guest toUpdate = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        return update(toUpdate, source);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(this::delete);
    }

    @Override
    public List<Guest> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Guest guest) {
        repository.delete(guest);
    }
}
