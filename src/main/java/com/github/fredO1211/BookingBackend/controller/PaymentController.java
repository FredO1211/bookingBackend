package com.github.fredO1211.BookingBackend.controller;

import com.github.fredO1211.BookingBackend.domain.Payment;
import com.github.fredO1211.BookingBackend.service.exception.EntityNotFoundException;
import com.github.fredO1211.BookingBackend.service.exception.IncorrectInputDataException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableCodeException;
import com.github.fredO1211.BookingBackend.service.impl.PaymentServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/payments")
public class PaymentController {
    final PaymentServiceImpl service;

    public PaymentController(PaymentServiceImpl service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Payment>> readAllPayments() {
        PageRequest page = PageRequest.of(0, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/page/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Payment>> readAllPayments(@PathVariable int index) {
        PageRequest page = PageRequest.of(index - 1, 12);
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/unpaid", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Payment>> getUnpaid() {
        PageRequest page = PageRequest.of(0, 12);
        return ResponseEntity.ok(service.getUnpaid(page));
    }

    @GetMapping(value = "/unpaid/page/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Payment>> getUnpaid(@PathVariable int index) {
        PageRequest page = PageRequest.of(index - 1, 12);
        return ResponseEntity.ok(service.getUnpaid(page));
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment toUpdate) {
        try {
            service.update(id, toUpdate);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException | UnavailableCodeException e) {
            throw e;
        } catch (Exception e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> togglePayment(@PathVariable Long id) {
        try {
            service.togglePayment(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
