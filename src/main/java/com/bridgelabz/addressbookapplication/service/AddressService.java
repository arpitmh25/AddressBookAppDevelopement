package com.bridgelabz.addressbookapplication.service;

import com.bridgelabz.addressbookapplication.dto.AddressBookDTO;
import com.bridgelabz.addressbookapplication.exception.AddressBookException;
import com.bridgelabz.addressbookapplication.model.Address;
import com.bridgelabz.addressbookapplication.repository.AddressRepository;
import com.bridgelabz.addressbookapplication.util.EmailSenderService;
import com.bridgelabz.addressbookapplication.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Slf4j generates logger instance
@Service
@Slf4j
public class AddressService implements IAddressBookService {

    //Autowired repository class to inject its dependency
    @Autowired
    AddressRepository repository;

    //Autowired Tokenutil  to inject its dependency
    @Autowired
    TokenUtil tokenutil;

    //Autowired EmailSenderService  to inject its dependency
    @Autowired
    private EmailSenderService sender;

    //Created service method which serves controller api to return welcome message
    public String getWelcome() {
        return "Welcome to Address Book !";
    }

    //Created service method which serves controller api to post data
    public String saveDataToRepo(AddressBookDTO addressBookDTO) {
        Address newAddress = new Address(addressBookDTO);
        repository.save(newAddress);
        String token = tokenutil.createToken(newAddress.getId());
        sender.sendEmail("arpitmh25@gmail.com", "Test Email", "Registered SuccessFully, Hi "
                + newAddress.getFirstName() + " click here -> " +
                "http://localhost:8080/addressbook/getAll/" + token);
        return token;
    }


    //Created service method which serves controller api to get record by token
    @Override
    public List<Address> getAddressBookDataToken(String token) {
        int id = tokenutil.decodeToken(token);
        Optional<Address> isContactPresent = repository.findById(id);
        if (isContactPresent.isPresent()) {
            List<Address> addressList = repository.findAll();
            return addressList;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    //Created  method which serves controller api to get record by token
    @Override
    public Address getRecordOfIdFromToken(String token) {
        Integer id = tokenutil.decodeToken(token);
        Optional<Address> address = repository.findById(id);
        if (address.isPresent()) {
            repository.getById(id);
        } else {
            throw new AddressBookException("Specific id not found");
        }
        return address.get();
    }


    //Created service method which serves controller api to update record by token
    @Override
    public Address updateRecordByToken(String token, AddressBookDTO addressBookDTO) {
        Integer id = tokenutil.decodeToken(token);
        Optional<Address> addressBook = repository.findById(id);
        if (addressBook.isEmpty()) {
            throw new AddressBookException("AddressBook details for id not found");
        }
        Address newBook = new Address(id, addressBookDTO);
        repository.save(newBook);
        sender.sendEmail("arpitmh25@gmail.com", "Test Email", "Updated SuccessFully, Hi "
                + newBook.getFirstName() + " click here -> " +
                "http://localhost:8080/addressbook/getAll/" + token);
        return newBook;
    }

    //Created service method which serves controller api to delete record by token
    @Override
    public Address deleteRecordByToken(String token) {
        Integer id = tokenutil.decodeToken(token);
        Optional<Address> newAddressBook = repository.findById(id);
        if (newAddressBook.isEmpty()) {
            throw new AddressBookException("Address Book Details not found");
        } else {
            repository.deleteById(id);
            sender.sendEmail("arpitmh25@gmail.com", "Test Email", "Deleted SuccessFully.. "
                    + newAddressBook.get() + " click here -> " +
                    "http://localhost:8080/addressbook/getAll/" + token);
        }
        return newAddressBook.get();
    }
}