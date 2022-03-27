package com.apollo.hotel.jpa.customer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {

        this.repository = repository;
    }

    @Override
    public Customer createCustomer(CreateCustomerParameters parameters) {
        LOGGER.debug("Creating customer {} ({})", parameters.getFullName().getFullName(), parameters.getEmail().asString());
        CustomerId customerId = repository.nextId();
        Customer customer = Customer.createCustomer(customerId,
                parameters.getFullName(),
                parameters.getGender(),
                parameters.getEmail(),
                parameters.getPhoneNumber());
        return repository.save(customer);
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public boolean customerWithEmailExists(Email email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Customer editCustomer(CustomerId customerId, EditCustomerParameters parameters) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        if (parameters.getVersion() != customer.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(Customer.class, customer.getId().asString());
        }

        parameters.update(customer);
        return customer;
    }

    @Override
    public Optional<Customer> getCustomer(CustomerId customerId) {

        return repository.findById(customerId);
    }

    @Override
    public void deleteCustomer(CustomerId customerId) {

        repository.deleteById(customerId);
    }

    @Override
    public long countCustomers() {
        return repository.count();
    }

    @Override
    public void deleteAllCustomers() {
        repository.deleteAll();
    }
}
