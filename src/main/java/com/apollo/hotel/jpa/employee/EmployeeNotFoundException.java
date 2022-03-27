package com.apollo.hotel.jpa.employee;


public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(EmployeeId employeeId) {
        super(String.format("Employee with id %s not found", employeeId.asString()));
    }
}
