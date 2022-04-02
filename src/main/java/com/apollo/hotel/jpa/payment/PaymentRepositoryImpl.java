package com.apollo.hotel.jpa.payment;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class PaymentRepositoryImpl implements PaymentRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public PaymentRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public PaymentId nextId() {
        return new PaymentId(generator.getNextUniqueId());
    }
}