package com.apollo.hotel.jpa.reservation;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class ReservationRepositoryImpl implements ReservationRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public ReservationRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public ReservationId nextId() {
        return new ReservationId(generator.getNextUniqueId());
    }
}