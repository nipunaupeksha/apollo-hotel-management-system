package com.apollo.hotel.jpa.employee;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;

import java.util.Objects;

public class NIC {

    private String nic;

    protected NIC(){}

    public NIC(String nic){
        Assert.hasText(nic, "email cannot be blank");
        this.nic = nic;
    }

    public String asString() {
        return nic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NIC that = (NIC) o;
        return Objects.equals(nic, that.nic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nic);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nic", nic)
                .toString();
    }
}
