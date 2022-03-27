package com.apollo.hotel.jpa.employee.web;

import com.apollo.hotel.jpa.employee.CreateEmployeeParameters;
import com.apollo.hotel.jpa.employee.*;

public class CreateEmployeeFormData extends AbstractEmployeeFormData {
    public CreateEmployeeParameters toParameters() {
        return new CreateEmployeeParameters(new FullName(getFirstName(), getLastName()),
                getGender(),
                new Email(getEmail()),
                new PhoneNumber(getPhoneNumber()), new NIC(getNic()));
    }
}
