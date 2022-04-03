package com.apollo.hotel.jpa.reservation.web;

import com.apollo.hotel.jpa.reservation.EditReservationParameters;
import com.apollo.hotel.jpa.reservation.Reservation;

public class EditReservationFormData extends AbstractReservationFormData {
    private String id;
    private long version;

    public static EditReservationFormData fromReservation(Reservation reservation) {
        EditReservationFormData result = new EditReservationFormData();
        result.setId(reservation.getId().asString());
        result.setVersion(reservation.getVersion());
        result.setCustomerId(reservation.getCustomer().getId());
        result.setCheckInDate(reservation.getCheckInDate());
        result.setCheckOutDate(reservation.getCheckOutDate());
        result.setRoomType(reservation.getRoomType());
        result.setReservationType(reservation.getReservationType());
        result.setAmount(reservation.getAmount());
        result.setPaymentMethod(reservation.getPaymentMethod());
        return result;
    }

    public EditReservationParameters toParameters() {
        return new EditReservationParameters(version,
                getCustomerId(),
                getCheckInDate(),
                getCheckOutDate(),
                getRoomType(),
                getReservationType(),
                getAmount(),
                getPaymentMethod());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
