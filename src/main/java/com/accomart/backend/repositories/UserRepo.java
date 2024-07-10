package com.accomart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accomart.backend.entities.Address;
import com.accomart.backend.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
	
	

}
