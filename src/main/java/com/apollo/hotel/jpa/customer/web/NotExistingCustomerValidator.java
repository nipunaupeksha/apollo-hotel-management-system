package com.apollo.hotel.jpa.customer.web;

import com.apollo.hotel.jpa.customer.Email;
import com.apollo.hotel.jpa.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistingCustomerValidator implements ConstraintValidator<NotExistingCustomer, AbstractCustomerFormData> {
    private final CustomerService customerService;

    @Autowired
    public NotExistingCustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void initialize(NotExistingCustomer constraint) {
        // intentionally empty
    }

    public boolean isValid(AbstractCustomerFormData formData, ConstraintValidatorContext context) {
        if (customerService.customerWithEmailExists(new Email(formData.getEmail()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{CustomerAlreadyExisting}")
                    .addPropertyNode("email")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
