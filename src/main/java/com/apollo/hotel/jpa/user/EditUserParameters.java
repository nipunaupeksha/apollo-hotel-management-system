package com.apollo.hotel.jpa.user;

public class EditUserParameters extends CreateUserParameters {
    private final long version;

    public EditUserParameters(long version, UserName userName, Gender gender, Type type, Email email, PhoneNumber phoneNumber) {
        super(userName, gender, type, email, phoneNumber);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(User user) {
        user.setUserName(getUserName());
        user.setGender(getGender());
        user.setType(getType());
        user.setEmail(getEmail());
        user.setPhoneNumber(getPhoneNumber());
    }
}