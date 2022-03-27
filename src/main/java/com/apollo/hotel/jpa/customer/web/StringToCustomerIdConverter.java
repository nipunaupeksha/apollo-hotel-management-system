package com.apollo.hotel.jpa.customer.web;

import com.apollo.hotel.jpa.customer.CustomerId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToCustomerIdConverter implements Converter<String, CustomerId> {
    @Override
    public CustomerId convert(String s) {
        return new CustomerId(UUID.fromString(s));
    }
}
