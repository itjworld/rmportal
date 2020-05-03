package com.rmportal.repositories;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.rmportal.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Role findByName(String name);
}
