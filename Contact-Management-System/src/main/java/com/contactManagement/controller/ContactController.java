package com.contactManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contactManagement.dao.ContactRepository;
import com.contactManagement.entities.Contact;
import com.contactManagement.exception.ContactNotFoundException;


@RestController
@RequestMapping("/losung360/contacts")
public class ContactController {
    
    @Autowired
    private ContactRepository contactRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        if(contacts.isEmpty()){
            return ResponseEntity.badRequest().body("Contacts Empty");
        }
        return ResponseEntity.ok(contacts);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody Contact contact) {
        if(contactRepository.findByEmail(contact.getEmail())!=null){
            return ResponseEntity.badRequest().body("Email Already Exist");
    
        }
        contactRepository.save(contact);
        return ResponseEntity.ok().body("Contact Created Successfull");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if(existingContact==null){
            return ResponseEntity.badRequest().body("Contact Not Found");
        }
        existingContact.setEmail(contact.getEmail());
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setPhoneNO(contact.getPhoneNO());
        contactRepository.save(existingContact);
        return ResponseEntity.ok().body("Contact Updated Sucessfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.ok().body("Contact Deleted");
    }

    @GetMapping("/search")
    public List<Contact> searchContacts(@RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName,
                                         @RequestParam(required = false) String email) {
        return contactRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(firstName, lastName, email);
    }
}
