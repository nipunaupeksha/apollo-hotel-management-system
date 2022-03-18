package com.apollo.hotel;

import com.apollo.hotel.jpa.user.*;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("init-db")
public class DatabaseInitializer implements CommandLineRunner {
    private final Faker faker = new Faker();
    private final UserService userService;

    public DatabaseInitializer(UserService userService){
        this.userService = userService;
    }

    @Override
    public void run(String... args){
        for(int i=0;i<20;i++){
            CreateUserParameters parameters = newRandomUserParameters();
            userService.createUser(parameters);
        }
    }

    private CreateUserParameters newRandomUserParameters(){
        Name name = faker.name();
        UserName userName = new UserName(name.firstName(), name.lastName());
        Gender gender = faker.bool().bool()?Gender.MALE: Gender.FEMALE;
        Type type = faker.bool().bool()?Type.ADMIN:Type.USER;
        Email email = new Email(faker.internet().emailAddress(generateEmailLocalPart(userName)));
        PhoneNumber phoneNumber = new PhoneNumber(faker.phoneNumber().phoneNumber());
        return new CreateUserParameters(userName, gender, type, email, phoneNumber);
    }

    private String generateEmailLocalPart(UserName userName) {
        return String.format("%s.%s",
                StringUtils.remove(userName.getFirstName().toLowerCase(), "'"),
                StringUtils.remove(userName.getLastName().toLowerCase(), "'"));
    }
}
