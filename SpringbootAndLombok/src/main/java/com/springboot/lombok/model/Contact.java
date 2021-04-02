package com.springboot.lombok.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;
 
    @Column(name = "First_Name")
    private String firstName;
 
    @Column(name = "Last_Name")
    private String lastName;
 
    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private ContactType type;
 
    @ManyToOne
    @JoinColumn(name = "Company_Id")
    private Company company;
 
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
 
    public ContactType getType() {
        return type;
    }
 
    public void setType(ContactType type) {
        this.type = type;
    }
 
    public Company getCompany() {
        return company;
    }
 
    public void setCompany(Company company) {
        this.company = company;
    }	
	
}
