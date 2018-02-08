package com.rmportal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.GuestDetail;
import java.lang.String;

import javax.persistence.QueryHint;

@Repository
public interface RoomBookDetailRepository extends JpaRepository<GuestDetail, Long> {
	
	
	@Query("SELECT COUNT(DISTINCT B.id) FROM GuestDetail B WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email) AND B.active=" + true)
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	long count(@Param("fName") String fName, @Param("mobile") String mobile, @Param("email") String email);
	
	@Query("SELECT B FROM GuestDetail B WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email) AND B.active=" + true)
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	Page<GuestDetail> findAll(@Param("fName") String fName, @Param("mobile") String mobile, @Param("email") String email, Pageable pageable);
	
	@Query("SELECT B FROM GuestDetail B WHERE B.active=:status")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	Page<GuestDetail> findAllByStatus(Pageable pageable, @Param("status") boolean status);
	
	@Query("SELECT COUNT(DISTINCT B.id) FROM GuestDetail B WHERE B.active=:status")
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	long countByStatus(@Param("status") boolean status);
	
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	GuestDetail findByEmail(String email);
	
}
