package com.apollo.hotel.jpa.employee.web;

import com.apollo.hotel.jpa.employee.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistingEmployeeValidator implements ConstraintValidator<NotExistingEmployee, AbstractEmployeeFormData> {
    private final EmployeeService employeeService;

    @Autowired
    public NotExistingEmployeeValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void initialize(NotExistingEmployee constraint) {
        // intentionally empty
    }

    public boolean isValid(AbstractEmployeeFormData formData, ConstraintValidatorContext context) {
        if (employeeService.employeeWithEmailExists(new Email(formData.getEmail()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{EmployeeAlreadyExisting}")
                    .addPropertyNode("email")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}