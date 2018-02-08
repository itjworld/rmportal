package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.rmportal.model.AddressInfo;
import com.rmportal.model.PortalInfo;
import java.util.List;

import javax.persistence.QueryHint;

@Repository
public interface AddressRepository extends JpaRepository<AddressInfo, Long> {
	
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<AddressInfo> findByLocation(PortalInfo location);
}
