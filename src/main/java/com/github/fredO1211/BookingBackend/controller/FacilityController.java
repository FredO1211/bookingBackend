package com.github.fredO1211.BookingBackend.controller;

import com.github.fredO1211.BookingBackend.domain.Facility;
import com.github.fredO1211.BookingBackend.service.FacilityService;
import com.github.fredO1211.BookingBackend.service.exception.IncorrectInputDataException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableNameException;
import com.github.fredO1211.BookingBackend.service.impl.FacilityServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/facilities")
public class FacilityController {
    final FacilityService service;

    public FacilityController(FacilityServiceImpl service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Facility>> readAllFacilities() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/page/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Facility>> readAllFacilities(@PathVariable int index) {
        PageRequest page = PageRequest.of(index-1, 12, Sort.by("name"));
        return ResponseEntity.ok(service.getAll(page));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createFacility(@RequestBody Facility toCreate) {
        try {
            var result = service.save(toCreate);
            return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        } catch (RuntimeException e) {
            throw new IncorrectInputDataException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody Facility toUpdate) {
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
    ResponseEntity<?> deleteFacility(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
