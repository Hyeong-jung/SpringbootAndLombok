package com.springboot.lombok.service;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.querydsl.core.types.Predicate;


import com.springboot.lombok.model.QContact;
import com.springboot.lombok.repository.ContactQuerydslRepository;
import com.springboot.lombok.model.Company;
import com.springboot.lombok.model.Contact;
import com.springboot.lombok.model.ContactType;




@Service
public class ContactService {
	
    @Autowired
    private ContactQuerydslRepository contactDslRepo;
 
    public List<Contact> searchByName(String firstName, String lastName) {
        List<Contact> ret = new ArrayList<>();
        QContact contEquation = QContact.contact;
        Predicate cnt = contEquation.firstName.contains(firstName);
        Iterable<Contact> contacts = contactDslRepo.findAll(cnt);
        for (Contact e : contacts) {
            ret.add(e);
        }
        return ret;
    }
 
    public Contact save(Company company, String firstName, String lastName, ContactType type) {
 
        Contact contact = new Contact();
        contact.setCompany(company);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setType(type);
 
        return contactDslRepo.save(contact);
    }
 
    public void delete(Contact contact) {
        contactDslRepo.delete(contact);
    }
	
}
