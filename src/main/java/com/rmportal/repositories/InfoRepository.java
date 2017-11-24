package com.rmportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmportal.model.PortalInfo;

@Repository
public interface InfoRepository extends JpaRepository<PortalInfo, Long> {

	List<PortalInfo> findByType(String type);

}
