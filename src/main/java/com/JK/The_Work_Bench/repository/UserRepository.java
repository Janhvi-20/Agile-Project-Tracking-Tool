package com.JK.The_Work_Bench.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.User;


public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	
}
