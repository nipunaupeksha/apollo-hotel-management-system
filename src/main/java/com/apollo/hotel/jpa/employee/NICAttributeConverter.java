package com.apollo.hotel.jpa.employee;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NICAttributeConverter implements AttributeConverter<NIC, String> {
    @Override
    public String convertToDatabaseColumn(NIC attribute) {
        return attribute.asString();
    }

    @Override
    public NIC convertToEntityAttribute(String dbData) {
        return new NIC(dbData);
    }
}
