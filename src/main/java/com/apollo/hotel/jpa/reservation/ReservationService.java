package com.apollo.hotel.jpa.reservation;

import com.google.common.collect.ImmutableSortedSet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationService {

    Reservation createReservation(CreateReservationParameters parameters);

    Reservation editReservation(ReservationId reservationId, EditReservationParameters parameters);

    Page<Reservation> getReservations(Pageable pageable);

    Optional<Reservation> getReservation(ReservationId reservationId);

    void deleteReservation(ReservationId reservationId);

    long countReservations();

    void deleteAllReservations();

    Page<Reservation> getAllReservationsUsingCheckInDate(Pageable pageable);
}
