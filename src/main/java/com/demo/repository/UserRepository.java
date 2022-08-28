package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query("SELECT * FROM USER WHERE email = ?1")
	Optional<User> getUserByEmail(String email);
}