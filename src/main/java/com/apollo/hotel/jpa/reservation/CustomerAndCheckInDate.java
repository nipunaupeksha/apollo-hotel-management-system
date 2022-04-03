package com.apollo.hotel.jpa.reservation;

import com.apollo.hotel.jpa.customer.Customer;

import java.time.LocalDate;

public class CustomerAndCheckInDate {

    private final Customer customer;
    private final LocalDate checkInDate;

    public CustomerAndCheckInDate(Customer customer, LocalDate checkInDate) {
        this.customer = customer;
        this.checkInDate = checkInDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }
}
