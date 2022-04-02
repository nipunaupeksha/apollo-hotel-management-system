package com.apollo.hotel.jpa.reservation.web;

import com.apollo.hotel.jpa.customer.CustomerId;
import com.apollo.hotel.jpa.reservation.ReservationType;
import com.apollo.hotel.jpa.reservation.RoomType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AbstractReservationFormData {
    @NotNull
    private CustomerId customerId;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkInDate;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate checkOutDate;

    @NotNull
    private RoomType roomType;

    @NotNull
    private ReservationType reservationType;

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
