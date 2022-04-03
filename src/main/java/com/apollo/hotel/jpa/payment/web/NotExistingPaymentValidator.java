package com.apollo.hotel.jpa.payment.web;

import com.apollo.hotel.jpa.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistingPaymentValidator implements ConstraintValidator<NotExistingPayment, AbstractPaymentFormData> {
    private final PaymentService paymentService;

    @Autowired
    public NotExistingPaymentValidator(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void initialize(NotExistingPayment constraint) {
        // intentionally empty
    }

    public boolean isValid(AbstractPaymentFormData formData, ConstraintValidatorContext context) {

        return true;
    }
}
