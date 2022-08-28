package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{}
