package com.apollo.hotel.jpa.payment.web;

import com.apollo.hotel.jpa.payment.Amount;
import com.apollo.hotel.jpa.payment.Status;
import com.apollo.hotel.jpa.reservation.ReservationId;

import javax.validation.constraints.NotNull;

public class AbstractPaymentFormData {
    @NotNull
    private ReservationId reservationId;

    @NotNull
    private Amount amount;

    @NotNull
    private Status status;

    public ReservationId getReservationId() {
        return reservationId;
    }

    public void setReservationId(ReservationId reservationId) {
        this.reservationId = reservationId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
