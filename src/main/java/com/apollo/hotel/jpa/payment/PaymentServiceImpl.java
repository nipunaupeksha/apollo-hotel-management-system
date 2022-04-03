package com.apollo.hotel.jpa.payment;

import com.apollo.hotel.jpa.customer.Customer;
import com.apollo.hotel.jpa.customer.CustomerId;
import com.apollo.hotel.jpa.customer.CustomerNotFoundException;
import com.apollo.hotel.jpa.reservation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements  PaymentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository repository;
    private final ReservationService reservationService;

    public PaymentServiceImpl(PaymentRepository repository, ReservationService reservationService) {
        this.repository = repository;
        this.reservationService = reservationService;
    }

    @Override
    public Payment createPayment(CreatePaymentParameters parameters) {
        LOGGER.debug("Creating payment amount: {}, status: {}", parameters.getAmount(), parameters.getStatus());
        PaymentId paymentId = repository.nextId();
        Payment payment = Payment.createPayment(paymentId,
                getReservation(parameters.getReservation()),
                parameters.getAmount(),
                parameters.getStatus());
        return repository.save(payment);
    }

    @Override
    public Payment editPayment(PaymentId paymentId, EditPaymentParameters parameters) {
        Payment payment = repository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
        if (parameters.getVersion() != payment.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(Reservation.class, payment.getId().asString());
        }
        payment.setReservation(getReservation(parameters.getReservation()));
        payment.setAmount(parameters.getAmount());
        payment.setStatus(parameters.getStatus());
        return payment;
    }

    @Override
    public Page<Payment> getPayments(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Payment> getPayment(PaymentId paymentId) {
        return repository.findById(paymentId);
    }

    @Override
    public void deletePayment(PaymentId paymentId) {
        repository.deleteById(paymentId);
    }

    @Override
    public long countPayments() {
        return repository.count();
    }

    @Override
    public void deleteAllPayments() {
        repository.deleteAll();
    }

    private Reservation getReservation(ReservationId reservationId) {
        return reservationService.getReservation(reservationId).orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }
}
