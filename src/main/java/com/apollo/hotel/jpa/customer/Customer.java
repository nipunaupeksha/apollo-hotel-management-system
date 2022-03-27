package com.apollo.hotel.jpa.customer;

import io.github.wimdeblauwe.jpearl.AbstractVersionedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ah_customer")
public class Customer extends AbstractVersionedEntity<CustomerId> {

    @NotNull
    private FullName fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private Email email;

    @NotNull
    private PhoneNumber phoneNumber;
    protected Customer() {
    }

    public Customer(CustomerId id,
                    FullName fullName,
                    Gender gender,
                    Email email,
                    PhoneNumber phoneNumber) {
        super(id);
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Customer createCustomer(CustomerId id,
                                  FullName fullName,
                                  Gender gender,
                                  Email email,
                                  PhoneNumber phoneNumber) {
        return new Customer(id, fullName, gender, email, phoneNumber);
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}