package com.github.fredO1211.BookingBackend.service.dto;

import com.github.fredO1211.BookingBackend.domain.Booking;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class SimplifiedBookingDTOAssembler extends RepresentationModelAssemblerSupport<Booking, SimplifiedBookingDTO> {

    public SimplifiedBookingDTOAssembler(Class<?> controllerClass, Class<SimplifiedBookingDTO> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected SimplifiedBookingDTO instantiateModel(Booking booking) {
        return new SimplifiedBookingDTO(booking);
    }

    @Override
    public SimplifiedBookingDTO toModel(Booking booking) {
        return createModelWithId(booking.getId(), booking);
    }


}
