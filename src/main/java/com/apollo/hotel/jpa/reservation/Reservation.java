package com.apollo.hotel.jpa.reservation;

import com.apollo.hotel.jpa.customer.Customer;
import io.github.wimdeblauwe.jpearl.AbstractVersionedEntity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="ah_reservation")
public class Reservation extends AbstractVersionedEntity<ReservationId> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @NotNull
    private LocalDate checkInDay;
    @NotNull
    private LocalDate checkOutDay;
    @NotNull
    @Enumerated(EnumType.STRING)
    ReservationType reservationType;
    @NotNull
    @Enumerated(EnumType.STRING)
    RoomType roomType;

    protected Reservation() {
    }

    private Reservation(ReservationId id,
                       Customer customer,
                       LocalDate checkInDay,
                       LocalDate checkOutDay,
                       RoomType roomType,
                       ReservationType reservationType) {
        super(id);
        this. customer= customer;
        this.checkInDay = checkInDay;
        this.checkOutDay = checkOutDay;
        this.roomType = roomType;
        this.reservationType = reservationType;
    }

    public static Reservation createReservation(ReservationId id,
                                                Customer customer,
                                                LocalDate checkInDay,
                                                LocalDate checkOutDay,
                                                RoomType roomType,
                                                ReservationType reservationType){
        return new Reservation(id, customer, checkInDay, checkOutDay, roomType, reservationType);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCheckInDay() {
        return checkInDay;
    }

    public void setCheckInDay(LocalDate checkInDay) {
        this.checkInDay = checkInDay;
    }

    public LocalDate getCheckOutDay() {
        return checkOutDay;
    }

    public void setCheckOutDay(LocalDate checkOutDay) {
        this.checkOutDay = checkOutDay;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}