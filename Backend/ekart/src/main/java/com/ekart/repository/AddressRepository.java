package com.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ekart.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{

}
