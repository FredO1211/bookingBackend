package com.github.fredO1211.BookingBackend.service.dto;

import com.github.fredO1211.BookingBackend.controller.FacilityController;
import com.github.fredO1211.BookingBackend.controller.GuestController;
import com.github.fredO1211.BookingBackend.controller.PaymentController;
import com.github.fredO1211.BookingBackend.domain.Booking;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Getter
@Setter(AccessLevel.PACKAGE)
public class ExtendsBookingDTO extends BookingDTO {
    private Link payment;
    private Link guest;
    private Link facility;

    ExtendsBookingDTO fillFields(Booking booking) {
        payment = linkTo(methodOn(PaymentController.class).getPaymentById(booking.getPayment().getId())).withSelfRel();
        guest = linkTo(methodOn(GuestController.class).getGuestId(booking.getGuest().getId())).withSelfRel();
        facility = linkTo(methodOn(FacilityController.class).getFacilityById(booking.getFacility().getId())).withSelfRel();
        super.fillFields(booking);
        return this;
    }

    public static ExtendsBookingDTO toDTO(Booking booking) {
        return new ExtendsBookingDTO().fillFields(booking);
    }
}
