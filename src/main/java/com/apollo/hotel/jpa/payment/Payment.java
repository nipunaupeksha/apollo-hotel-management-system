package com.apollo.hotel.jpa.payment;


import com.apollo.hotel.jpa.reservation.Reservation;
import io.github.wimdeblauwe.jpearl.AbstractVersionedEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ah_payment")
public class Payment extends AbstractVersionedEntity<PaymentId> {

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;
    @NotNull
    private Amount amount;
    @NotNull
    private Status status;


    protected Payment() {
    }

    private Payment(PaymentId id, Reservation reservation, Amount amount, Status status) {
        super(id);
        this.reservation = reservation;
        this.amount = amount;
        this.status = status;
    }

    public static Payment createPayment(PaymentId id, Reservation reservation, Amount amount, Status status){
        return new Payment(id, reservation, amount, status);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Amount getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}