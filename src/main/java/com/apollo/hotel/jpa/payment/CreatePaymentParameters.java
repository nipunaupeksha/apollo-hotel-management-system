package com.apollo.hotel.jpa.payment;


import com.apollo.hotel.jpa.reservation.ReservationId;

public class CreatePaymentParameters {
    private final ReservationId reservationId;
    private final Amount amount;
    private final Status status;

    public CreatePaymentParameters(Amount amount, Status status, ReservationId reservationId) {
        this.amount = amount;
        this.status = status;
        this.reservationId = reservationId;
    }

    public Amount getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public ReservationId getReservation(){
        return reservationId;
    }
}
