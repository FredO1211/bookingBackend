package com.github.fredO1211.BookingBackend.controller;

import com.github.fredO1211.BookingBackend.service.MailService;
import com.github.fredO1211.BookingBackend.service.dto.MessageContentDTO;
import com.github.fredO1211.BookingBackend.service.impl.EmailServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/message")
public class EmailController {

    private final MailService mailService;

    public EmailController(EmailServiceImpl mailService) {
        this.mailService = mailService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendMessageToAllGuests(@RequestBody MessageContentDTO messageContentDTO) {
        mailService.sendToAll(messageContentDTO);
    }

    @PostMapping(params = {"/{guest_id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendMessageToCurrentGuest(@RequestParam Long guestId, @RequestBody MessageContentDTO messageContentDTO) {
        mailService.sendToCurrent(guestId, messageContentDTO);
    }
}
