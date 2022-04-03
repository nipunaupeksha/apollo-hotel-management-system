package com.apollo.hotel.jpa.payment;

public class Amount {

    private double amount;

    protected Amount(){}

    public Amount(double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
