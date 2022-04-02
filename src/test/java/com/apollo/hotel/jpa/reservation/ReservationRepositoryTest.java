package com.apollo.hotel.jpa.reservation;

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
class ReservationRepositoryTest {
    private final ReservationRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ReservationRepositoryTest(ReservationRepository repository,
                           JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSaveReservation() {
        ReservationId id = repository.nextId();
        //repository.save(new Reservation(id));

        entityManager.flush();

        UUID idInDb = jdbcTemplate.queryForObject("SELECT id FROM reservation", UUID.class);
        assertThat(idInDb).isEqualTo(id.getId());
    }
}