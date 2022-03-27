package com.apollo.hotel.jpa.customer;

import com.sun.istack.Nullable;

public class CreateCustomerParameters {

    private final FullName fullName;
    private final Gender gender;
    private final Email email;
    private final PhoneNumber phoneNumber;

    public CreateCustomerParameters(FullName fullName,  Gender gender, Email email, PhoneNumber phoneNumber) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
