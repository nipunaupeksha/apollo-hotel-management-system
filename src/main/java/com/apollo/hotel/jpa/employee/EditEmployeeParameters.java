package com.apollo.hotel.jpa.employee;

public class EditEmployeeParameters extends CreateEmployeeParameters{
    private final long version;

    public EditEmployeeParameters(long version, FullName fullName, Gender gender, Email email, PhoneNumber phoneNumber, NIC nic) {
        super(fullName, gender,  email, phoneNumber, nic);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(Employee employee) {
        employee.setFullName(getFullName());
        employee.setGender(getGender());
        employee.setEmail(getEmail());
        employee.setPhoneNumber(getPhoneNumber());
        employee.setNic(getNic());
    }
}
