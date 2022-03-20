package com.apollo.hotel.jpa.user;

import io.github.wimdeblauwe.jpearl.AbstractVersionedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "ah_user")
public class User extends AbstractVersionedEntity<UserId> {

    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    private Set<UserRole> roles;

    @NotNull
    private String password;

    @NotNull
    private UserName userName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private Email email;

    @NotNull
    private PhoneNumber phoneNumber;

    protected User() {
    }

    public User(UserId id,
                Set<UserRole> roles,
                UserName userName,
                String password,
                Gender gender,
                Email email,
                PhoneNumber phoneNumber) {
        super(id);
        this.userName = userName;
        this.roles = roles;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User createUser(UserId id,
                                  UserName userName,
                                  String encodedPassword,
                                  Gender gender,
                                  Email email,
                                  PhoneNumber phoneNumber) {
        return new User(id, Stream.of(UserRole.USER).collect(Collectors.toCollection(HashSet::new)),
                userName, encodedPassword, gender, email, phoneNumber);
    }

    public static User createAdministrator(UserId id,
                                           UserName userName,
                                           String encodedPassword,
                                           Gender gender,
                                           Email email,
                                           PhoneNumber phoneNumber) {
        return new User(id, Stream.of(UserRole.USER, UserRole.ADMIN).collect(Collectors.toCollection(HashSet::new)),
                userName, encodedPassword, gender, email, phoneNumber);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public UserName getUserName() {
        return userName;
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

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}