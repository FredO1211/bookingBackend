package com.github.fredO1211.BookingBackend.service;

import com.github.fredO1211.BookingBackend.domain.Payment;

public interface PaymentService extends CrudService<Payment, Long>{
    public void togglePayment(Long id);
}
