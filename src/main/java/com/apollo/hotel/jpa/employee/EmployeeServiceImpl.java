package com.apollo.hotel.jpa.employee;

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
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {

        this.repository = repository;
    }

    @Override
    public Employee createEmployee(CreateEmployeeParameters parameters) {
        LOGGER.debug("Creating Employee {} ({})", parameters.getFullName().getFullName(), parameters.getEmail().asString());
        EmployeeId employeeId= repository.nextId();
        Employee employee = Employee.createEmployee(employeeId,
                parameters.getFullName(),
                parameters.getGender(),
                parameters.getEmail(),
                parameters.getPhoneNumber(),
                parameters.getNic());
        return repository.save(employee);
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public boolean employeeWithEmailExists(Email email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Employee editEmployee(EmployeeId employeeId, EditEmployeeParameters parameters) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        if (parameters.getVersion() != employee.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(Employee.class, employee.getId().asString());
        }

        parameters.update(employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployee(EmployeeId employeeId) {
        return repository.findById(employeeId);
    }

    @Override
    public void deleteEmployee(EmployeeId employeeId) {
        repository.deleteById(employeeId);
    }

    @Override
    public long countEmployees() {
        return repository.count();
    }

    @Override
    public void deleteAllEmployees() {
        repository.deleteAll();
    }
}
