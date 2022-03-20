package com.apollo.hotel.jpa.user.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupOne;
import com.apollo.hotel.infrastructure.validation.ValidationGroupTwo;
import com.apollo.hotel.jpa.user.Gender;
import com.apollo.hotel.jpa.user.UserRole;

import javax.validation.constraints.*;

@NotExistingUser(groups = ValidationGroupTwo.class)
public class AbstractUserFormData {
    @NotNull
    private UserRole userRole;
    @NotBlank
    @Size(min = 1, max = 200, groups = ValidationGroupOne.class)
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 200, groups = ValidationGroupOne.class)
    private String lastName;
    @NotNull
    private Gender gender;
    @NotBlank
    @Email(groups = ValidationGroupOne.class)
    private String email;

    @NotBlank
    @Pattern(regexp = "[0-9.\\-() x/+]+", groups = ValidationGroupOne.class)
    private String phoneNumber;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

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
}

