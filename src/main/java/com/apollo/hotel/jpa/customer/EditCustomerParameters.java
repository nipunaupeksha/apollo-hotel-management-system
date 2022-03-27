package com.apollo.hotel.jpa.customer;

public class EditCustomerParameters extends CreateCustomerParameters{

    private final long version;

    public EditCustomerParameters(long version, FullName fullName, Gender gender, Email email, PhoneNumber phoneNumber) {
        super(fullName, gender,  email, phoneNumber);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(Customer customer) {
        customer.setFullName(getFullName());
        customer.setGender(getGender());
        customer.setEmail(getEmail());
        customer.setPhoneNumber(getPhoneNumber());
    }
}
