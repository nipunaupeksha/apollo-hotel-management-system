package com.apollo.hotel.jpa.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(CreateCustomerParameters parameters);

    Page<Customer> getCustomers(Pageable pageable);

    boolean customerWithEmailExists(Email email);

    Customer editCustomer(CustomerId customerId, EditCustomerParameters parameters);

    Optional<Customer> getCustomer(CustomerId customerId);

    void deleteCustomer(CustomerId customerId);

    long countCustomers();

    void deleteAllCustomers();
}
