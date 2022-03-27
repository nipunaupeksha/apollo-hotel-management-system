package com.apollo.hotel.jpa.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CustomerRepository extends PagingAndSortingRepository<Customer, CustomerId>, CustomerRepositoryCustom {
    boolean existsByEmail(Email email);

    Optional<Customer> findByEmail(Email email);
}