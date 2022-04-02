package com.apollo.hotel.jpa.reservation;

import com.apollo.hotel.jpa.customer.CustomerId;

import java.time.LocalDate;

public class CreateReservationParameters {

    private final CustomerId customerId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final RoomType roomType;
    private final ReservationType reservationType;

    public CreateReservationParameters(CustomerId customerId,
                                       LocalDate checkInDate,
                                       LocalDate checkOutDate,
                                       RoomType roomType,
                                       ReservationType reservationType){
        this.customerId = customerId;
        this.checkInDate  = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.reservationType = reservationType;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }
}
