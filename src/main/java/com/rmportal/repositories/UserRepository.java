package com.rmportal.repositories;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u from User u WHERE u.username = :username AND u.password = :password AND u.status=" + true)
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	User validate(@Param("username") String username, @Param("password") String password);

	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	@Query("SELECT u from User u WHERE u.username = :username OR u.email = :email")
	User findByUsername(@Param("username") String username, @Param("email") String email);
	
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	User findByUsername(String username);
}
