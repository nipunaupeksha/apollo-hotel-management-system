package com.apollo.hotel.jpa.employee;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {
    private final EmployeeRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    EmployeeRepositoryTest(EmployeeRepository repository,
                           JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSaveEmployee() {
        EmployeeId id = repository.nextId();
        //repository.save(new Employee(id));

        entityManager.flush();

        UUID idInDb = jdbcTemplate.queryForObject("SELECT id FROM employee", UUID.class);
        assertThat(idInDb).isEqualTo(id.getId());
    }
}