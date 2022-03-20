package com.apollo.hotel.jpa.user.web;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, CreateUserFormData> {
    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreateUserFormData value, ConstraintValidatorContext context) {
        if (!value.getPassword().equals(value.getPasswordRepeated())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{PasswordsNotMatching}")
                    .addPropertyNode("passwordRepeated")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
