package com.apollo.hotel.jpa.customer;

public class CustomerNameAndId {
    private final CustomerId id;
    private final FullName fullName;

    public CustomerNameAndId(CustomerId id, FullName fullName){
        this.id= id;
        this.fullName=fullName;
    }

    public CustomerId getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }
}
