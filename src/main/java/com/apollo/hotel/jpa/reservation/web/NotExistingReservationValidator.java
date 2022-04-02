package com.apollo.hotel.jpa.reservation.web;

import com.apollo.hotel.jpa.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NotExistingReservationValidator implements ConstraintValidator<NotExistingReservation, AbstractReservationFormData> {
    private final ReservationService reservationService;

    @Autowired
    public NotExistingReservationValidator(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void initialize(NotExistingReservation constraint) {
        // intentionally empty
    }

    public boolean isValid(AbstractReservationFormData formData, ConstraintValidatorContext context) {

        return true;
    }
}
