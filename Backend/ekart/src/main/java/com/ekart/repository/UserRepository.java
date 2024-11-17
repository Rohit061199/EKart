package com.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekart.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByEmail(String email);

}
