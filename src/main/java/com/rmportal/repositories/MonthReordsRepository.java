package com.rmportal.repositories;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.rmportal.model.MonthRecords;

public interface MonthReordsRepository extends JpaRepository<MonthRecords, Integer> {

	@Query("SELECT count(*) FROM MonthRecords M WHERE month=:month AND year=:year")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	int findByMonth(@Param(value = "month") int month, @Param(value = "year") int year);
}
