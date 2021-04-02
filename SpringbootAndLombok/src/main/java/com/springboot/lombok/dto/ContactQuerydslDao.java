package com.springboot.lombok.dto;

import java.util.List;

import com.springboot.lombok.model.Contact;

public interface ContactQuerydslDao {

    List<Contact> getContactById(Long id);
    
    List<Contact> getContactFromTypedQuery(Long id);
 
    List<Contact> getContactFromDynamicQuery(String firstName, String lastName);	
	
}
