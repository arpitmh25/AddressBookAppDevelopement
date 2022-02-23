package com.bridgelabz.addressbookapplication.repository;

import com.bridgelabz.addressbookapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

//Created AddressBookRepository extending JpaRepository so that we perform CRUD and can implement custom query methods
public interface AddressRepository extends JpaRepository<Address, Integer> {

}

