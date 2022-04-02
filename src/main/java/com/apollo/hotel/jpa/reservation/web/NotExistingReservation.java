package com.apollo.hotel.jpa.reservation.web;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistingReservationValidator.class)
public @interface NotExistingReservation {
    String message() default "{ReservationAlreadyExisting}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
