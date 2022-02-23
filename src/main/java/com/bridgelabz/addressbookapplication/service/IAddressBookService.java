package com.bridgelabz.addressbookapplication.service;

import com.bridgelabz.addressbookapplication.dto.AddressBookDTO;
import com.bridgelabz.addressbookapplication.model.Address;

import java.util.List;

//Created interface for all service methods to achieve abstraction
public interface IAddressBookService {

    public String getWelcome();

    public Address saveDataToRepo(AddressBookDTO addressBookDTO);

    public Address getRecordById(Integer id);

    public List<Address> getRecord();

    public Address updateRecordById(Integer id, AddressBookDTO addressBookDTO);

    public String deleteRecordById(Integer id);
}