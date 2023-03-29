package com.contactManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactManagement.entities.Contact;


public interface ContactRepository extends JpaRepository<Contact,Long> {

    Contact findByEmail(String email);
    List<Contact> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String firstName, String lastName, String email);
    
}
