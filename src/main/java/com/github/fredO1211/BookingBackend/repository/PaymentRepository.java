package com.github.fredO1211.BookingBackend.repository;

import com.github.fredO1211.BookingBackend.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(nativeQuery = true, value = "SELECT count(*)<1 FROM PAYMENTS WHERE CODE=:code")
    boolean isCodeAvailable(String code);

    @Query(nativeQuery = true, value = "SELECT * FROM PAYMENTS WHERE NOT IS_ADVANCE_PAID")
    Page<Payment> unpaidList(Pageable pageable);

}
