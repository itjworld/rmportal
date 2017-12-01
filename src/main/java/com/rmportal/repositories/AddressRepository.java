package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.PortalInfo;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressInfo, Long> {
	List<AddressInfo> findByLocation(PortalInfo location);
}
