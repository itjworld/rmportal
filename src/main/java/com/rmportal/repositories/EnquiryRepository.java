package com.rmportal.repositories;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.Enquiry;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
	
	
	@Query("SELECT E from Enquiry E WHERE E.email = :email OR E.mobile = :mobile")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	Enquiry findByForEmailAndMobile(@Param("email") String email,@Param("mobile") String mobile);
}
