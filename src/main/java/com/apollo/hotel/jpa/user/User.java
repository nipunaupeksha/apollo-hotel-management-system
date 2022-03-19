package com.apollo.hotel.jpa.user;

import io.github.wimdeblauwe.jpearl.AbstractVersionedEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="ah_user")
public class User extends AbstractVersionedEntity<UserId> {

    @NotNull
    private UserName userName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    private Email email;

    @NotNull
    private PhoneNumber phoneNumber;

    protected User() {
    }

    public User(UserId id,
                UserName userName,
                Gender gender,
                Type type,
                Email email,
                PhoneNumber phoneNumber) {
        super(id);
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

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}