package com.apollo.hotel.jpa.user;

public class UserServiceException extends RuntimeException{
    public UserServiceException(Exception e){
        super(e);
    }
}
