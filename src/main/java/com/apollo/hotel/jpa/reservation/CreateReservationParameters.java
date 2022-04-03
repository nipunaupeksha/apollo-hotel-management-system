package com.apollo.hotel.jpa.reservation;

import com.apollo.hotel.jpa.customer.CustomerId;

import java.time.LocalDate;

public class CreateReservationParameters {

    private final CustomerId customerId;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final RoomType roomType;
    private final ReservationType reservationType;
    private final Amount amount;
    private final PaymentMethod paymentMethod;

    public CreateReservationParameters(CustomerId customerId,
                                       LocalDate checkInDate,
                                       LocalDate checkOutDate,
                                       RoomType roomType,
                                       ReservationType reservationType,
                                       Amount amount,
                                       PaymentMethod paymentMethod){
        this.customerId = customerId;
        this.checkInDate  = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.reservationType = reservationType;
        this.amount = amount;
        this.paymentMethod =paymentMethod;
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

    public Amount getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
