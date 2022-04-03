package com.apollo.hotel.jpa.payment.web;


import com.apollo.hotel.jpa.payment.CreatePaymentParameters;

public class CreatePaymentFormData extends AbstractPaymentFormData{

    public CreatePaymentParameters toParameters() {
        return new CreatePaymentParameters( getAmount(), getStatus(), getReservationId());
    }
}
