package com.apollo.hotel.jpa.customer;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(CustomerId customerId) {
        super(String.format("Customer with id %s not found", customerId.asString()));
    }
}
