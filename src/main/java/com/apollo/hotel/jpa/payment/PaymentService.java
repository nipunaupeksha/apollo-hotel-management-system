package com.apollo.hotel.jpa.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PaymentService {
    Payment createPayment(CreatePaymentParameters parameters);

    Payment editPayment(PaymentId paymentId, EditPaymentParameters parameters);

    Page<Payment> getPayments(Pageable pageable);

    Optional<Payment> getPayment(PaymentId paymentId);

    void deletePayment(PaymentId paymentId);

    long countPayments();

    void deleteAllPayments();
}
