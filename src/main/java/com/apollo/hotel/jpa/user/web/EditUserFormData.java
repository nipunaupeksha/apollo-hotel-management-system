package com.apollo.hotel.jpa.user.web;

import com.apollo.hotel.jpa.user.*;

public class EditUserFormData extends AbstractUserFormData {
    private String id;
    private long version;

    public static EditUserFormData fromUser(User user) {
        EditUserFormData result = new EditUserFormData();
        result.setId(user.getId().asString());
        result.setVersion(user.getVersion());
        result.setFirstName(user.getUserName().getFirstName());
        result.setLastName(user.getUserName().getLastName());
        result.setGender(user.getGender());
        result.setEmail(user.getEmail().asString());
        result.setPhoneNumber(user.getPhoneNumber().asString());

        return result;
    }

    public EditUserParameters toParameters() {
        return new EditUserParameters(version,
                new UserName(getFirstName(), getLastName()),
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