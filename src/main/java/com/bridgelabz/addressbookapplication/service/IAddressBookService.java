package com.bridgelabz.addressbookapplication.service;

import com.bridgelabz.addressbookapplication.dto.AddressBookDTO;
import com.bridgelabz.addressbookapplication.model.Address;

import java.util.List;

//Created interface for all service methods to achieve abstraction
public interface IAddressBookService {

    public String getWelcome();

    public String saveDataToRepo(AddressBookDTO addressBookDTO);

    public List<Address> getAddressBookDataToken(String token);

    public Address getRecordOfIdFromToken(String token);

    public Address updateRecordByToken(String token, AddressBookDTO addressBookDTO);

    public Address deleteRecordByToken(String token);

}