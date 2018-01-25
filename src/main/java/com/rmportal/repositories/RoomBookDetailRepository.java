package com.rmportal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.RoomBookDetails;

@Repository
public interface RoomBookDetailRepository extends JpaRepository<RoomBookDetails, Long> {
	
	
	@Query("SELECT COUNT(DISTINCT B.id) FROM RoomBookDetails B WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email)")
	long count(@Param("fName") String fName, @Param("mobile") String mobile, @Param("email") String email);
	
	@Query("SELECT B FROM RoomBookDetails B WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email)")
	Page<RoomBookDetails> findAll(@Param("fName") String fName, @Param("mobile") String mobile, @Param("email") String email, Pageable pageable);
	
}
