package com.rmportal.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.GuestDetail;
import com.rmportal.model.GuestPayment;

@Repository
public interface GuestPaymentRepository extends JpaRepository<GuestPayment, Long> {
	List<GuestPayment> findByGuestDetail(GuestDetail guestdetail);
	
	@Query("SELECT g from GuestPayment g WHERE g.guestDetail.email = :email")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<GuestPayment> findByEmail(@Param("email") String email);
	
	@Query("SELECT g from GuestPayment g WHERE g.guestDetail.id = :id")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<GuestPayment> findByGuestDetailId(@Param("id") long id);
	
	
}
