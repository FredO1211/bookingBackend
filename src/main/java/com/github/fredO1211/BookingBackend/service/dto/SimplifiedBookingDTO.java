package com.github.fredO1211.BookingBackend.service.dto;

import com.github.fredO1211.BookingBackend.domain.Booking;
import com.github.fredO1211.BookingBackend.service.DateProvider;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Getter
public class SimplifiedBookingDTO extends RepresentationModel<SimplifiedBookingDTO> {
    LocalDate startingDate;
    LocalDate endingDate;
    List<LocalDate> otherDatesTaken;

    public SimplifiedBookingDTO(Booking booking) {
        this(booking.getStartOfBooking(),
                booking.getEndOfBooking());

        this.otherDatesTaken = DateProvider.getDatesBetweenOthers(
                booking.getStartOfBooking(),
                booking.getEndOfBooking());
    }

    private SimplifiedBookingDTO(LocalDate startingDate, LocalDate endingDate) {
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
}
