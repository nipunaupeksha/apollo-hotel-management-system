package com.apollo.hotel.jpa.employee.web;

import com.apollo.hotel.jpa.employee.*;

public class EditEmployeeFormData extends AbstractEmployeeFormData{
    private String id;
    private long version;

    public static EditEmployeeFormData fromEmployee(Employee employee) {
        EditEmployeeFormData result = new EditEmployeeFormData();
        result.setId(employee.getId().asString());
        result.setVersion(employee.getVersion());
        result.setFirstName(employee.getFullName().getFirstName());
        result.setLastName(employee.getFullName().getLastName());
        result.setGender(employee.getGender());
        result.setEmail(employee.getEmail().asString());
        result.setPhoneNumber(employee.getPhoneNumber().asString());
        result.setNic(employee.getNic().asString());

        return result;
    }

    public EditEmployeeParameters toParameters() {
        return new EditEmployeeParameters(version,
                new FullName(getFirstName(), getLastName()),
                getGender(),
                new Email(getEmail()),
                new PhoneNumber(getPhoneNumber()),
                new NIC(getNic()));
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
