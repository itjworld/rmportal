package com.rmportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmportal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u from User u WHERE u.username = :username AND u.password = :password AND u.status=" + true)
	User validate(@Param("username") String username, @Param("password") String password);

	User findByUsername(String username);
}
