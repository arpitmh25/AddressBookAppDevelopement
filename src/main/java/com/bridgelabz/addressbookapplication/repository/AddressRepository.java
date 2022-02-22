package com.bridgelabz.addressbookapplication.repository;

import com.bridgelabz.addressbookapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {

}

