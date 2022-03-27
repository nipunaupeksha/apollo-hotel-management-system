package com.apollo.hotel.jpa.customer;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public CustomerRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public CustomerId nextId() {
        return new CustomerId(generator.getNextUniqueId());
    }
}