package com.apollo.hotel.jpa.reservation;

import com.apollo.hotel.jpa.customer.CustomerId;

import java.time.LocalDate;

public class EditReservationParameters extends CreateReservationParameters{

    private final long version;

    public EditReservationParameters(long version, CustomerId customerId, LocalDate checkInDate,
                                     LocalDate checkOutDate, RoomType roomType, ReservationType reservationType,Amount amount, PaymentMethod paymentMethod){
        super(customerId, checkInDate, checkOutDate, roomType, reservationType, amount, paymentMethod);
        this.version = version;
    }

    public long getVersion(){
        return version;
    }

}
