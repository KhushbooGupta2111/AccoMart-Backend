package com.accomart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accomart.backend.entities.Address;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    List<Address> findByUserUserId(int userId);
}
