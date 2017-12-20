package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.PortalMappingInfo;

@Repository
public interface PortalMappingRepository extends JpaRepository<PortalMappingInfo, Long> {

	@Query("SELECT u from PortalMappingInfo u WHERE u.address.id = :addressId AND u.roomNumber = :roomNumber")
	PortalMappingInfo getMapping(@Param("addressId") Long addressId, @Param("roomNumber") Integer roomNumber);
}
