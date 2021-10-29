package com.github.fredO1211.BookingBackend.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageDTO extends MessageContentDTO{
    List<String> emails;

    public MessageDTO(String title, String message, List<String> emails) {
        super(title, message);
        this.emails = emails;
    }
}
