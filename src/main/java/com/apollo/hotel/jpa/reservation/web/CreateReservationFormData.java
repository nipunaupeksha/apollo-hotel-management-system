package com.apollo.hotel.jpa.reservation.web;

import com.apollo.hotel.jpa.reservation.CreateReservationParameters;

public class CreateReservationFormData extends AbstractReservationFormData{

    public CreateReservationParameters toParameters(){
        CreateReservationParameters parameters = new CreateReservationParameters(getCustomerId(),
                getCheckInDate(),
                getCheckOutDate(),
                getRoomType(),
                getReservationType());
        return parameters;
    }
}
