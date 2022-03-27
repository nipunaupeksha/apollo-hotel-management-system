package com.apollo.hotel.jpa.customer.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupOne;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroupOne.class})
public interface EditCustomerValidationGroupSequence {
}
