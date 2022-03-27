package com.apollo.hotel.jpa.employee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, EmployeeId>, EmployeeRepositoryCustom {
    boolean existsByEmail(Email email);

    Optional<Employee> findByEmail(Email email);
}