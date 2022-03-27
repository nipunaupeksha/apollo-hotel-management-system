package com.apollo.hotel.jpa.user;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class EditUserParameters extends CreateUserParameters {
    private final long version;

    public EditUserParameters(long version, UserName userName, Gender gender,  Email email, PhoneNumber phoneNumber) {
        super(userName, null, gender,  email, phoneNumber);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(User user) {
        user.setUserName(getUserName());
        user.setGender(getGender());
        user.setEmail(getEmail());
        user.setPhoneNumber(getPhoneNumber());

        MultipartFile avatar = getAvatar();
        if(avatar!=null){
            try{
                user.setAvatar(avatar.getBytes());
            }catch(IOException e){
                throw new UserServiceException(e);
            }
        }
    }
}