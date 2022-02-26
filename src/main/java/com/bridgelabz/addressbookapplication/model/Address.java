package com.bridgelabz.addressbookapplication.model;

import com.bridgelabz.addressbookapplication.dto.AddressBookDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Map to a database table address
@Entity
//Use to bundle features of getter and setter
@Data

//Created Address class with different fields
public class Address {
    @Id
    @GeneratedValue
    private Integer id;


    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String city;
    private String state;
    private Integer zip;

    //Default
    public Address() {
        super();
    }

    //Created constructor for updatedataby id method
    public Address(Integer id, AddressBookDTO addressBookDTO) {
        this.id = id;
        this.firstName = addressBookDTO.getFirstName();
        this.lastName = addressBookDTO.getLastName();
        this.email = addressBookDTO.getEmail();
        this.phoneNumber = addressBookDTO.getPhoneNumber();
        this.city = addressBookDTO.getCity();
        this.state = addressBookDTO.getState();
        this.zip = addressBookDTO.getZip();
    }

    //Created constructor for savedatatorepo method
    public Address(AddressBookDTO addressBookDTO) {
        this.firstName = addressBookDTO.getFirstName();
        this.lastName = addressBookDTO.getLastName();
        this.phoneNumber = addressBookDTO.getPhoneNumber();
        this.city = addressBookDTO.getCity();
        this.state = addressBookDTO.getState();
        this.zip = addressBookDTO.getZip();
        this.email = addressBookDTO.getEmail();
    }
}
