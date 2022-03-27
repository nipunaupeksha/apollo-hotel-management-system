package com.apollo.hotel.jpa.employee;


public class CreateEmployeeParameters {
    private final FullName fullName;
    private final Gender gender;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final NIC nic;

    public CreateEmployeeParameters(FullName fullName, Gender gender, Email email, PhoneNumber phoneNumber, NIC nic) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
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

    public NIC getNic(){return nic;}
}
