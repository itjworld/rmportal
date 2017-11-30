package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmportal.model.AddressInfo;

@Repository
public interface AddressRepository extends JpaRepository<AddressInfo, Long> {
}
