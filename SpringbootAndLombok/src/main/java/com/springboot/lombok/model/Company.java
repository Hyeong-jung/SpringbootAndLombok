package com.springboot.lombok.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
 
    @Column(name = "NAME")
    private String name;
 
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CompanyType type;
 
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> contacts;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public CompanyType getType() {
        return type;
    }
 
    public void setType(CompanyType type) {
        this.type = type;
    }
 
    public List<Contact> getContacts() {
        return contacts;
    }
 
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
	
}
