package com.springboot.lombok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lombok.model.Book;
import com.springboot.lombok.model.Company;
import com.springboot.lombok.model.Contact;
import com.springboot.lombok.model.ContactType;
import com.springboot.lombok.service.ContactService;

import lombok.extern.slf4j.Slf4j;

//Lombok 로거 필드 생성
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class ContactController {

	
    @Autowired
    ContactService contactService;
    
    
    // URL - http://localhost:8000/api/contacts
    @GetMapping(value = "contacts")
    public ResponseEntity<List<Contact>> getContacts(@RequestBody HashMap<String, String> paramMap) {
        log.info(" Contact DB 호출");
        
        String firstName = paramMap.get("firstName");  
        String lastName = paramMap.get("lastName");
                
        final List<Contact> contacts = contactService.searchByName(firstName, lastName);
        
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    
    
    
    @PostMapping(value = "contact/save")
    public ResponseEntity<Void> save(@RequestBody final Company company, String firstName, String lastName, ContactType type) {
        log.info("DB 에 contact 데이터 저장", company.toString());
        
        // save(Company company, String firstName, String lastName, ContactType type)
        contactService.save(company, firstName, lastName, type);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }    
    
	
}
