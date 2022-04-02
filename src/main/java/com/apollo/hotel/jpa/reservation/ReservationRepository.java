package com.apollo.hotel.jpa.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, ReservationId>, ReservationRepositoryCustom {
    @Query(value = "SELECT * FROM ah_reservation order by checkin_data",
    nativeQuery = true)
    Page<Reservation> getReservationsByCheckInDate(Pageable pageable);
}