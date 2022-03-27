package com.apollo.hotel.jpa.customer.web;

import com.apollo.hotel.jpa.customer.*;

public class EditCustomerFormData extends AbstractCustomerFormData{
    private String id;
    private long version;

    public static EditCustomerFormData fromCustomer(Customer customer) {
        EditCustomerFormData result = new EditCustomerFormData();
        result.setId(customer.getId().asString());
        result.setVersion(customer.getVersion());
        result.setFirstName(customer.getFullName().getFirstName());
        result.setLastName(customer.getFullName().getLastName());
        result.setGender(customer.getGender());
        result.setEmail(customer.getEmail().asString());
        result.setPhoneNumber(customer.getPhoneNumber().asString());

        return result;
    }

    public EditCustomerParameters toParameters() {
        return new EditCustomerParameters(version,
                new FullName(getFirstName(), getLastName()),
                getGender(),
                new Email(getEmail()),
                new PhoneNumber(getPhoneNumber()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
