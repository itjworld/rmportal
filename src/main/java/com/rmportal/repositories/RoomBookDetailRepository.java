package com.rmportal.repositories;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.GuestDetail;

@Repository
public interface RoomBookDetailRepository extends JpaRepository<GuestDetail, Long> {

	@Query("SELECT COUNT(DISTINCT B.id) FROM GuestDetail B WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email) AND B.active="
			+ true)
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	long count(@Param("fName") String fName, @Param("mobile") String mobile, @Param("email") String email);

	@Query("SELECT B FROM GuestDetail B WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email) AND B.active="
			+ true)
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<GuestDetail> findAll(@Param("fName") String fName, @Param("mobile") String mobile,
			@Param("email") String email, Pageable pageable);

	@Query("SELECT B FROM GuestDetail B JOIN B.mapping M WHERE (B.fName LIKE :fName OR B.mobile LIKE :mobile OR B.email LIKE :email) AND M.address.id=:addressId  AND B.active="
			+ true)
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<GuestDetail> findAll(@Param("fName") String fName, @Param("mobile") String mobile,
			@Param("email") String email, @Param("addressId") Long addressId, Pageable pageable);

	@Query(value = "SELECT B FROM GuestDetail B JOIN FETCH B.paymentList P WHERE B.active=:status AND month(P.createDateTime)=:month AND year(P.createDateTime)=:year",
			countQuery = "SELECT COUNT(B) FROM GuestDetail B JOIN  B.paymentList P WHERE B.active=:status AND month(P.createDateTime)=:month AND year(P.createDateTime)=:year")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<GuestDetail> findAllByStatus(Pageable pageable, @Param("status") boolean status, @Param("month") int month,
			@Param("year") int year);

	@Query("SELECT B FROM GuestDetail B WHERE B.active=:status")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<GuestDetail> findAllByStatus(Pageable pageable, @Param("status") boolean status);

	@Query("SELECT B FROM GuestDetail B JOIN B.mapping M WHERE B.active=:status AND M.address.id=:addressId")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<GuestDetail> findAllByStatus(Pageable pageable, @Param("status") boolean status,
			@Param("addressId") Long addressId);

	@Query("SELECT B FROM GuestDetail B JOIN B.paymentList P JOIN B.mapping M WHERE B.active=:status AND M.address.id=:addressId AND "
			+ "month(P.createDateTime)=:month AND year(P.createDateTime)=:year")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Page<GuestDetail> findAllByStatus(Pageable pageable, @Param("status") boolean status,
			@Param("addressId") Long addressId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT B FROM GuestDetail B JOIN FETCH B.paymentList M WHERE B.active=:status AND month(M.createDateTime)=:month AND year(M.createDateTime)=:year")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<GuestDetail> findAllByMonth(@Param("status") boolean status, @Param("month") int month,
			@Param("year") int year);
	
	@Query("SELECT B FROM GuestDetail B JOIN FETCH B.paymentList M WHERE B.id=:id AND month(M.createDateTime)=:month AND year(M.createDateTime)=:year")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	GuestDetail findIdAndByMonth(@Param("id") long id, @Param("month") int month,
			@Param("year") int year);

//	@Query("select e from Event e where year(e.eventDate) = ?1 and month(e.eventDate) = ?2")
//	List<Entity> getByYearAndMonth(int year, int month);

	@Query("SELECT COUNT(DISTINCT B.id) FROM GuestDetail B WHERE B.active=:status")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	long countByStatus(@Param("status") boolean status);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	GuestDetail findByEmail(String email);

	@Query("SELECT B FROM GuestDetail B WHERE B.active=:status")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	List<GuestDetail> findByStatus(@Param("status") boolean status);
}
