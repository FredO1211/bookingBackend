package com.github.fredO1211.BookingBackend.service;

import com.github.fredO1211.BookingBackend.service.dto.MessageContentDTO;
import com.github.fredO1211.BookingBackend.service.dto.MessageDTO;

public interface MailService {
    void send(MessageDTO message);
    void sendToAll(MessageContentDTO messageContentDTO);
    void sendToCurrent(Long guestId, MessageContentDTO messageContentDTO);

}
