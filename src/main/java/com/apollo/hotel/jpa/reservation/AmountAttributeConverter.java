package com.apollo.hotel.jpa.reservation;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AmountAttributeConverter implements AttributeConverter<Amount, String> {
    @Override
    public String convertToDatabaseColumn(Amount attribute) {
        return attribute.getAmount();
    }

    @Override
    public Amount convertToEntityAttribute(String dbData) {
        return new Amount(dbData);
    }
}
