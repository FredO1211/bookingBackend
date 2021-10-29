package com.github.fredO1211.BookingBackend.service.impl;

import com.github.fredO1211.BookingBackend.service.MailService;
import com.github.fredO1211.BookingBackend.service.dto.MessageContentDTO;
import com.github.fredO1211.BookingBackend.service.dto.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements MailService {
    @Override
    public void send(MessageDTO message) {

    }

    @Override
    public void sendToAll(MessageContentDTO messageContentDTO) {

    }

    @Override
    public void sendToCurrent(Long guestId, MessageContentDTO messageContentDTO) {

    }

//    private final RabbitTemplate rabbitTemplate;
//    private final GuestService guestService;
//
//    public EmailServiceImpl(RabbitTemplate rabbitTemplate, GuestServiceImpl guestService) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.guestService = guestService;
//    }
//
//    @Override
//    public void send(MessageDTO message) {
//        rabbitTemplate.convertAndSend("mail", message);
//    }
//
//    @Override
//    public void sendToAll(MessageContentDTO messageContentDTO) {
//        var messageDTO = MessageContentDTO.map(messageContentDTO);
//        List<Guest> mails = guestService.getAll();
//        fillMailList(messageDTO, mails);
//        rabbitTemplate.convertAndSend("mail",messageDTO);
//    }
//
//    private void fillMailList(MessageDTO messageDTO, List<Guest> mails) {
//        if (mails.isEmpty()) {
//            throw new NoMailsFoundException();
//        }
//        messageDTO.setEmails(mails.stream()
//                .map(Guest::getEmail).collect(Collectors.toList()));
//    }
//
//    @Override
//    public void sendToCurrent(Long guestId, MessageContentDTO messageContentDTO) {
//        var messageDTO = MessageContentDTO.map(messageContentDTO);
//        List<Guest> mails = Collections
//                .singletonList(guestService.getById(guestId)
//                        .orElseThrow(EntityNotFoundException::new));
//        fillMailList(messageDTO, mails);
//        rabbitTemplate.convertAndSend("mail",messageDTO);
//    }
}
