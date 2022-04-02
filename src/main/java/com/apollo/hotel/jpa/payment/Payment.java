package com.apollo.hotel.jpa.payment;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Payment extends AbstractEntity<PaymentId> {

    /**
     * Default constructor for JPA
     */
    protected Payment() {
    }

    public Payment(PaymentId id) {
        super(id);
    }
}