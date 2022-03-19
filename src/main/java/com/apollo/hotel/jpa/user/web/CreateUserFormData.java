package com.apollo.hotel.jpa.user.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupOne;
import com.apollo.hotel.infrastructure.validation.ValidationGroupTwo;
import com.apollo.hotel.jpa.user.*;

import javax.validation.constraints.*;
import javax.validation.constraints.Email;

@NotExistingUser(groups=ValidationGroupTwo.class)
public class CreateUserFormData {
    @NotBlank
    @Size(min=1, max=200, groups= ValidationGroupOne.class)
    private String firstName;
    @NotBlank
    @Size(min=1, max=200, groups = ValidationGroupTwo.class)
    private String lastName;
    @NotNull
    private Gender gender;
    @NotNull
    private Type type;
    @NotBlank
    @Email(groups=ValidationGroupOne.class)
    private String email;
    @NotBlank
    @Pattern(regexp = "[0-9.\\-() x/+]+", groups = ValidationGroupOne.class)
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CreateUserParameters toParameters(){
        return new CreateUserParameters(new UserName(firstName, lastName), gender, type,
                new com.apollo.hotel.jpa.user.Email(email),
                new PhoneNumber(phoneNumber));
    }
}
