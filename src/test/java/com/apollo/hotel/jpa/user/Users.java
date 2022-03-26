package com.apollo.hotel.jpa.user;

import java.util.UUID;

public class Users {

    public static User createUser(){
        return createUser(new UserName("Henry", "Cross"));
    }

    public static User createUser(UserName userName){
        return User.createUser(new UserId(UUID.randomUUID()),
                userName, "fake-encoded-password",
                Gender.FEMALE, new Email(String.format("%s.%s@gmail.com", userName.getFirstName(), userName.getLastName())),
                new PhoneNumber("+555 666 777"));
    }
}
