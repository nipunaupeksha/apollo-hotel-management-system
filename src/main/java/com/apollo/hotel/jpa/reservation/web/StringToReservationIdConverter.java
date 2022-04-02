package com.apollo.hotel.jpa.reservation.web;

import com.apollo.hotel.jpa.reservation.ReservationId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToReservationIdConverter implements Converter<String, ReservationId> {
    @Override
    public ReservationId convert(String s) {
        return new ReservationId(UUID.fromString(s));
    }
}
