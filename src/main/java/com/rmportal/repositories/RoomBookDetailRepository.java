package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmportal.model.RoomBookDetails;

@Repository
public interface RoomBookDetailRepository extends JpaRepository<RoomBookDetails, Long> {
}
