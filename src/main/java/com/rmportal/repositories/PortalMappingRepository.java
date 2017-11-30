package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmportal.model.PortalMappingInfo;

@Repository
public interface PortalMappingRepository extends JpaRepository<PortalMappingInfo, Long> {
}
