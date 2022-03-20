package com.apollo.hotel.jpa.user.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupOne;
import com.apollo.hotel.infrastructure.validation.ValidationGroupTwo;
import com.apollo.hotel.jpa.user.*;

import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@PasswordsMatch(groups = ValidationGroupTwo.class)
public class CreateUserFormData extends AbstractUserFormData {
    @NotBlank
    private String password;
    @NotBlank
    private String passwordRepeated;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public CreateUserParameters toParameters() {
        return new CreateUserParameters(new UserName(getFirstName(), getLastName()),
                password,
                getGender(),
                new com.apollo.hotel.jpa.user.Email(getEmail()),
                new PhoneNumber(getPhoneNumber()));
    }
}
