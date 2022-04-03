package com.apollo.hotel.jpa.reservation;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;

import java.util.Objects;

public class Amount {

    private String amount;

    protected Amount(){}

    public Amount(String amount){
        Assert.hasText(amount, "amount cannot be blank");
        Assert.isTrue(amount.contains("."), "amount should contain decimal point");
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Amount that = (Amount) o;
        return Objects.equals(amount, that.amount);
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("amount", amount)
                .toString();
    }
}
