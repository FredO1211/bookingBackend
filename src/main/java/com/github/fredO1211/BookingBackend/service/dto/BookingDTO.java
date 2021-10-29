package com.github.fredO1211.BookingBackend.service.dto;

import com.github.fredO1211.BookingBackend.domain.Booking;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
public class BookingDTO {
    @Future
    private LocalDate startOfBooking;
    @Future
    private LocalDate endOfBooking;
    private String description;
    @Positive
    private int countOfGuests;

    BookingDTO fillFields(Booking booking) {
        startOfBooking = booking.getStartOfBooking();
        endOfBooking = booking.getEndOfBooking();
        description = booking.getDescription();
        countOfGuests = booking.getCountOfGuests();
        return this;
    }

    public static BookingDTO toDTO(Booking booking){
        return new BookingDTO().fillFields(booking);
    }
}
