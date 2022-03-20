package com.apollo.hotel.jpa.user.web;

import com.apollo.hotel.jpa.user.Email;
import com.apollo.hotel.jpa.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, AbstractUserFormData> {

    private final UserService userService;

    @Autowired
    public NotExistingUserValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(NotExistingUser constraint) {
        // intentionally empty
    }

    public boolean isValid(AbstractUserFormData formData, ConstraintValidatorContext context) { //<.>
        if (userService.userWithEmailExists(new Email(formData.getEmail()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{UserAlreadyExisting}")
                    .addPropertyNode("email")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
