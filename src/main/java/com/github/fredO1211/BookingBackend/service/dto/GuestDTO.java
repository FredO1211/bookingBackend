package com.github.fredO1211.BookingBackend.service.dto;

import com.github.fredO1211.BookingBackend.domain.Guest;
import lombok.Getter;

@Getter
public class GuestDTO {
    private String name;
    private String email;
    private String phoneNumber;

    GuestDTO(Guest guest) {
        this.name = guest.getName();
        this.email = guest.getEmail();
        this.phoneNumber = guest.getPhoneNumber();
    }
}
