package com.github.fredO1211.BookingBackend.service.impl;

import com.github.fredO1211.BookingBackend.controller.BookingController;
import com.github.fredO1211.BookingBackend.domain.Booking;
import com.github.fredO1211.BookingBackend.domain.Payment;
import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;
import com.github.fredO1211.BookingBackend.repository.BookingRepository;
import com.github.fredO1211.BookingBackend.service.*;
import com.github.fredO1211.BookingBackend.service.dto.BookingDTO;
import com.github.fredO1211.BookingBackend.service.dto.MessageDTO;
import com.github.fredO1211.BookingBackend.service.dto.SimplifiedBookingDTO;
import com.github.fredO1211.BookingBackend.service.dto.SimplifiedBookingDTOAssembler;
import com.github.fredO1211.BookingBackend.service.exception.EntityNotFoundException;
import com.github.fredO1211.BookingBackend.service.exception.UnavailableDateException;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService, Validator<Booking> {
    public final BookingRepository repository;
    public final GuestService guestService;
    public final PaymentService paymentService;
    public final MailService provider;

    public BookingServiceImpl(BookingRepository repository, GuestServiceImpl guestService, PaymentServiceImpl paymentService, EmailServiceImpl provider) {
        this.repository = repository;
        this.guestService = guestService;
        this.paymentService = paymentService;
        this.provider = provider;
    }

    @Override
    @Transactional
    public Booking save(@Valid Booking booking) {
        Payment paymentToSave = booking.getPayment();
        paymentService.save(paymentToSave);

        if (booking.getGuest().getId() == null) {
            guestService.save(booking.getGuest());
        }

        BookingValidator.validNewBooking(booking, paymentToSave);
        Booking result = repository.save(valid(booking));

        provider.send(new MessageDTO(MessageProvider.MAIL_TITLE,
                MessageProvider.MAIL_MSG,
                Collections.singletonList(result.getGuest().getEmail())));

        return result;
    }

    @Override
    public List<Booking> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CollectionModel<SimplifiedBookingDTO> getSimplifiedBookingDTOList(YearMonth month, Long facilityId) {
        LocalDate from = LocalDate.of(month.getYear(), month.getMonth(), 1);
        LocalDate to = LocalDate.of(month.getYear(), month.getMonth(), month.lengthOfMonth());

        List<Booking> bookingList = repository.getBookingsBetweenDates(from.toString(), to.toString(), facilityId);

        return new SimplifiedBookingDTOAssembler(
                BookingController.class,
                SimplifiedBookingDTO.class)
                .toCollectionModel(bookingList);
    }

    @Override
    public Booking update(Long id, @Valid Booking source) {
        Booking toUpdate = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        toUpdate.setCountOfGuests(source.getCountOfGuests());
        toUpdate.setStartOfBooking(source.getStartOfBooking());
        toUpdate.setEndOfBooking(source.getEndOfBooking());
        toUpdate.setFacility(source.getFacility());
        return toUpdate;
    }

    public Booking update(Booking toUpdate, @Valid BookingDTO source) {
        valid(toUpdate, source);
        toUpdate.setCountOfGuests(source.getCountOfGuests());
        toUpdate.setStartOfBooking(source.getStartOfBooking());
        toUpdate.setEndOfBooking(source.getEndOfBooking());
        return repository.save(toUpdate);
    }

    @Override
    public Booking update(Long id, BookingDTO source) {
        Booking toUpdate = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        return update(toUpdate, source);
    }

    @Override
    public void delete(Booking booking) {
        repository.delete(booking);
    }


    public void delete(Long id) {
        repository.findById(id).ifPresent(this::delete);
    }

    @Override
    public Booking valid(Booking booking) {
        if (!repository.isAvailable(
                booking.getStartOfBooking().toString(),
                booking.getEndOfBooking().toString(),
                booking.getFacility().getId())) {
            throw new UnavailableDateException();
        }
        return booking;
    }

    public BookingDTO valid(Booking currentBooking, BookingDTO source) {
        List<LocalDate> datesToCheck = DateProvider.getOtherDates(
                currentBooking.getStartOfBooking(),
                currentBooking.getEndOfBooking(),
                source.getStartOfBooking(),
                source.getEndOfBooking());

        datesToCheck.forEach(d -> {
            if (repository.isAvailable(d.toString(), currentBooking.getId())) {
                throw new UnavailableDateException();
            }
        });
        return source;
    }
}
