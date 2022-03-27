package com.apollo.hotel.jpa.customer.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupTwo;
import com.apollo.hotel.jpa.customer.CreateCustomerParameters;
import com.apollo.hotel.jpa.customer.*;

public class CreateCustomerFormData extends AbstractCustomerFormData {

    public CreateCustomerParameters toParameters() {
        return new CreateCustomerParameters(new FullName(getFirstName(), getLastName()),
                getGender(),
                new com.apollo.hotel.jpa.customer.Email(getEmail()),
                new PhoneNumber(getPhoneNumber()));
    }
}
