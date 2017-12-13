package com.bolster.repository;

import org.springframework.data.repository.CrudRepository;

import com.bolster.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
