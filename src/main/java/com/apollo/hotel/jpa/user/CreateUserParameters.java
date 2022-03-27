package com.apollo.hotel.jpa.user;

import com.sun.istack.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class CreateUserParameters {
    private final UserName userName;
    private final String password;
    private final Gender gender;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private MultipartFile avatar;

    public CreateUserParameters(UserName userName, String password,Gender gender, Email email, PhoneNumber phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserName getUserName() {
        return userName;
    }

    @Nullable
    public String getPassword() {
        return password;
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
