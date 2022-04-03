package com.apollo.hotel.jpa.payment.web;

import com.apollo.hotel.jpa.payment.EditPaymentParameters;
import com.apollo.hotel.jpa.payment.Payment;

public class EditPaymentFormData extends AbstractPaymentFormData{

    private String id;
    private long version;

    public static EditPaymentFormData fromPayment(Payment payment){
        EditPaymentFormData result = new EditPaymentFormData();
        result.setId(payment.getId().asString());
        result.setAmount(payment.getAmount());
        result.setStatus(payment.getStatus());
        result.setReservationId(payment.getReservation().getId());
        return result;
    }

    public EditPaymentParameters toParameters(){
        return new EditPaymentParameters(
                getAmount(),
                getStatus(),
                getReservationId(),
                getVersion()
        );
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
