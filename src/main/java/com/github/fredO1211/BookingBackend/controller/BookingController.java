package com.github.fredO1211.BookingBackend.controller;

import com.github.fredO1211.BookingBackend.domain.Booking;
import com.github.fredO1211.BookingBackend.service.BookingService;
import com.github.fredO1211.BookingBackend.service.dto.ExtendsBookingDTO;
import com.github.fredO1211.BookingBackend.service.dto.SimplifiedBookingDTO;
import com.github.fredO1211.BookingBackend.service.exception.IncorrectInputDataException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableNameException;
import com.github.fredO1211.BookingBackend.service.impl.BookingServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.YearMonth;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bookings")
public class BookingController {
    final BookingService service;

    public BookingController(BookingServiceImpl service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Booking>> readAllBookings() {
        PageRequest page = PageRequest.of(0, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/page/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Booking>> readAllBookings(@PathVariable int index) {
        PageRequest page = PageRequest.of(index - 1, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/marks", params = {"date", "facility_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CollectionModel<SimplifiedBookingDTO>> getSimplifiedBookingDTOByMonth(@RequestParam("facility_id") Long id, @RequestParam YearMonth date) {
        return ResponseEntity.ok(service.getSimplifiedBookingDTOList(date, id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createBooking(@RequestBody Booking toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(ExtendsBookingDTO.toDTO(result));
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking toUpdate) {
        try {
            service.update(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (UnavailableNameException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
