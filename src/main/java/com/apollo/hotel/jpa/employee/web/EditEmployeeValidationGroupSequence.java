package com.apollo.hotel.jpa.employee.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupOne;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;
@GroupSequence({Default.class, ValidationGroupOne.class})
public interface EditEmployeeValidationGroupSequence {
}
