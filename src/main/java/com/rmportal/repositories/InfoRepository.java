package com.rmportal.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.rmportal.model.PortalInfo;

@Repository
public interface InfoRepository extends JpaRepository<PortalInfo, Long> {

	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<PortalInfo> findByType(String type);

}
