package com.apollo.hotel.jpa.reservation;

import com.apollo.hotel.jpa.customer.Customer;
import com.apollo.hotel.jpa.customer.CustomerId;
import com.apollo.hotel.jpa.customer.CustomerNotFoundException;
import com.apollo.hotel.jpa.customer.CustomerService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private final ReservationRepository repository;
    private final CustomerService customerService;

    public ReservationServiceImpl(ReservationRepository repository, CustomerService customerService){
        this.repository = repository;
        this.customerService = customerService;
    }

    @Override
    public Reservation createReservation(CreateReservationParameters parameters) {
        LOGGER.debug("Creating reservation check-in: {}, check-out: {}", parameters.getCheckInDate(), parameters.getCheckOutDate());
        ReservationId reservationId = repository.nextId();
        Reservation reservation = Reservation.createReservation(reservationId,
                getCustomer(parameters.getCustomerId()),
                parameters.getCheckInDate(),
                parameters.getCheckOutDate(),
                parameters.getRoomType(),
                parameters.getReservationType());
        return repository.save(reservation);
    }

    @Override
    public Reservation editReservation(ReservationId reservationId, EditReservationParameters parameters) {
        Reservation reservation = repository.findById(reservationId).orElseThrow(()->new ReservationNotFoundException(reservationId));
        if(parameters.getVersion()!=reservation.getVersion()){
            throw new ObjectOptimisticLockingFailureException(Reservation.class, reservation.getId().asString());
        }
        reservation.setCustomer(getCustomer(parameters.getCustomerId()));
        reservation.setCheckInDate(parameters.getCheckInDate());
        reservation.setCheckOutDate(parameters.getCheckOutDate());
        reservation.setRoomType(parameters.getRoomType());
        reservation.setReservationType(parameters.getReservationType());
        return reservation;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Reservation> getReservations(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Reservation> getReservation(ReservationId reservationId) {
        return repository.findById(reservationId);
    }

    @Override
    public void deleteReservation(ReservationId reservationId) {
        repository.deleteById(reservationId);
    }

    @Override
    public long countReservations() {
        return repository.count();
    }

    @Override
    public void deleteAllReservations() {
    repository.deleteAll();
    }

    @Override
    public Page<Reservation> getAllReservationsUsingCheckInDate(Pageable pageable) {
        return repository.getReservationsByCheckInDate(pageable);
    }

    private Customer getCustomer(CustomerId customerId){
        return customerService.getCustomer(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
    }
}