package com.apollo.hotel.jpa.employee;

import io.github.wimdeblauwe.jpearl.AbstractVersionedEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ah_employee")
public class Employee extends AbstractVersionedEntity<EmployeeId> {
    @NotNull
    private FullName fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private Email email;

    @NotNull
    private PhoneNumber phoneNumber;

    @NotNull
    private NIC nic;

    protected Employee() {
    }

    public Employee(EmployeeId id,
                    FullName fullName,
                    Gender gender,
                    Email email,
                    PhoneNumber phoneNumber,
                    NIC nic) {
        super(id);
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
    }

    public static Employee createEmployee(EmployeeId id,
                                          FullName fullName,
                                          Gender gender,
                                          Email email,
                                          PhoneNumber phoneNumber,
                                          NIC nic) {
        return new Employee(id, fullName, gender, email, phoneNumber, nic);
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public NIC getNic() {
        return nic;
    }

    public void setNic(NIC nic) {
        this.nic = nic;
    }
}