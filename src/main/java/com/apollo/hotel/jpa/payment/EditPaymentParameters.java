package com.apollo.hotel.jpa.payment;

import com.apollo.hotel.jpa.reservation.ReservationId;

public class EditPaymentParameters extends CreatePaymentParameters{

    private final long version;

    public EditPaymentParameters(Amount amount, Status status, ReservationId reservationId, long version) {
        super(amount, status, reservationId);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

}
