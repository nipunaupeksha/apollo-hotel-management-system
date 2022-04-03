package com.apollo.hotel.jpa.payment;

import com.apollo.hotel.jpa.reservation.ReservationId;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(PaymentId paymentId){
        super(String.format("{Payment} with id %s not found.", paymentId.asString()));
    }
}
