package com.springboot.lombok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.springboot.lombok.model.Company;
import com.springboot.lombok.model.Contact;


public interface ContactQuerydslRepository extends JpaRepository<Contact, Long>, QuerydslPredicateExecutor<Contact> {
	
	public List<Contact> findByCompany(Company company);

}
