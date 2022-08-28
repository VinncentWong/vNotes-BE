package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query(value = "SELECT * FROM users WHERE email = ?1 LIMIT 1", nativeQuery = true)
	Optional<User> getUserByEmail(String email);
}
