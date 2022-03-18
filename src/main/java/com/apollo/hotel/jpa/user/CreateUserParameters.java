package com.apollo.hotel.jpa.user;

public class CreateUserParameters {
    private final UserName userName;
    private final Gender gender;
    private final Type type;
    private final Email email;
    private final PhoneNumber phoneNumber;

    public CreateUserParameters(UserName userName, Gender gender, Type type, Email email, PhoneNumber phoneNumber) {
        this.userName = userName;
        this.gender = gender;
        this.type = type;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserName getUserName() {
        return userName;
    }

    public Gender getGender() {
        return gender;
    }

    public Type getType() {
        return type;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
