package com.apollo.hotel.jpa.payment.web;

import com.apollo.hotel.jpa.payment.PaymentId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToPayementIdConverter implements Converter<String, PaymentId> {
    @Override
    public PaymentId convert(String s) {
        return new PaymentId(UUID.fromString(s));
    }
}
