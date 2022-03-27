package com.apollo.hotel.jpa.employee.web;

import com.apollo.hotel.jpa.employee.EmployeeId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToEmployeeIdConverter implements Converter<String, EmployeeId> {
    @Override
    public EmployeeId convert(String s) {
        return new EmployeeId(UUID.fromString(s));
    }
}
