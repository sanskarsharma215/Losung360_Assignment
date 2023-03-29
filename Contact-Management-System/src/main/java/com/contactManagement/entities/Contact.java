package com.contactManagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @NotBlank(message = "First Name is Required")
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is Required")
    private String email;

    @Column(nullable = false , length = 10)
    @NotBlank(message = "Phone Number is Required")
    private String phoneNO;

    public Contact(){
        super();
    }

    

    public Contact(@NotBlank(message = "First Name is Required") String firstName, String lastName,
            @NotBlank(message = "Email is Required") String email,
            @NotBlank(message = "Phone Number is Required") String phoneNO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNO = phoneNO;
    }


    

    @Override
    public String toString() {
        return "Contacts [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", phoneNO=" + phoneNO + "]";
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    
    
}
