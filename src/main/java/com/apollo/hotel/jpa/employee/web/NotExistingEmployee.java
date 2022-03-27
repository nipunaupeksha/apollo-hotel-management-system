package com.apollo.hotel.jpa.employee.web;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistingEmployeeValidator.class)
public @interface NotExistingEmployee {
    String message() default "{EmployeeAlreadyExisting}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}