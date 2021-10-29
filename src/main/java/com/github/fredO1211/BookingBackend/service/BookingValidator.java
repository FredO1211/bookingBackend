package com.github.fredO1211.BookingBackend.service;

import com.github.fredO1211.BookingBackend.domain.Booking;
import com.github.fredO1211.BookingBackend.domain.Payment;
import com.github.fredO1211.BookingBackend.messageprovider.MessageProvider;

public class BookingValidator {
    public static void validNewBooking(Booking booking, Payment payment){
        int stayCost = getStayCost(booking,payment);
        if (payment.getDiscount() > stayCost) {
            throw new IllegalArgumentException(MessageProvider.BOOKING_DISCOUNT_HIGHER_THAN_STAY_COST_MSG);
        }
        if (payment.getAdvanceSize() > stayCost) {
            throw new IllegalArgumentException(MessageProvider.BOOKING_ADVANCE_HIGHER_THAN_STAY_COST_MSG);
        }
    }
    public static int getStayCost(Booking booking, Payment payment) {
        return booking.getStayLength() * payment.getCostPerNight() - payment.getDiscount();
    }
}
