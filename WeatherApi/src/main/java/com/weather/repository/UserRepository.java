package com.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

}
