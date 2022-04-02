package com.apollo.hotel.jpa.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(ReservationId reservationId){
        super(String.format("Reservation with id %s not found.", reservationId.asString()));
    }
}
