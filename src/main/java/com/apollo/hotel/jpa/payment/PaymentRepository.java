package com.apollo.hotel.jpa.payment;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PaymentRepository extends PagingAndSortingRepository<Payment, PaymentId>, PaymentRepositoryCustom {
}