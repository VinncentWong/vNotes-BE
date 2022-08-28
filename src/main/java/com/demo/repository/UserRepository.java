package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}
