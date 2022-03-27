package com.apollo.hotel.jpa.employee;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public EmployeeRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public EmployeeId nextId() {
        return new EmployeeId(generator.getNextUniqueId());
    }
}