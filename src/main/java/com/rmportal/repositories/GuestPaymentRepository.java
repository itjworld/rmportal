package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmportal.model.GuestPayment;

@Repository
public interface GuestPaymentRepository extends JpaRepository<GuestPayment, Long> {
	
}
