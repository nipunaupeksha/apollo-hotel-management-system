package com.apollo.hotel.jpa.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(CreateEmployeeParameters parameters);

    Page<Employee> getEmployees(Pageable pageable);

    boolean employeeWithEmailExists(Email email);

    Employee editEmployee(EmployeeId employeeId, EditEmployeeParameters parameters);

    Optional<Employee> getEmployee(EmployeeId employeeId);

    void deleteEmployee(EmployeeId employeeId);

    long countEmployees();

    void deleteAllEmployees();
}
