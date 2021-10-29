package com.github.fredO1211.BookingBackend.controller;

import com.github.fredO1211.BookingBackend.domain.Guest;
import com.github.fredO1211.BookingBackend.service.dto.GuestDTO;
import com.github.fredO1211.BookingBackend.service.exception.EntityNotFoundException;
import com.github.fredO1211.BookingBackend.service.exception.IncorrectInputDataException;
import com.github.fredO1211.BookingBackend.service.impl.GuestServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/guests")
public class GuestController {
    final GuestServiceImpl service;

    public GuestController(GuestServiceImpl service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Guest>> readAllGuests() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/page/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Guest>> readAllGuests(@PathVariable int index) {
        PageRequest page = PageRequest.of(index - 1, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> getGuestId(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/_search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Guest>> readGuestsByProps(@RequestBody GuestDTO guest) {
        return ResponseEntity.ok(service.getByProps(guest));
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateGuest(@PathVariable Long id, @RequestBody Guest toUpdate) {
        try {
            service.update(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new IncorrectInputDataException(e);
        }
    }
}
