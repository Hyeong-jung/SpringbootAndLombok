package com.springboot.lombok.model;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "user")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    
    private String name;
    
    private String phoneNum;
    
    //// ##### Step1. @Embedded, @Embeddable 사용 할 멤버 Start #####
    //private String zipCode; 
    //private String address1;
    //private String address2;
    //// ##### Step1. @Embedded, @Embeddable 사용 할 멤버 End #####
    
    //// ##### Step2. @Embedded, @Embeddable 사용 할 Class Start #####
    //@Embedded
    //private Address address;
    //// ##### Step2. @Embedded, @Embeddable 사용 할 Class End #####
    
    // ##### Step3. @Embedded, @Embeddable 사용 할 다중 Class Start #####
    @Embedded
    @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    @AttributeOverride(name = "address1", column = @Column(name = "home_address1"))
    @AttributeOverride(name = "address2", column = @Column(name = "home_address2"))
    private Address homeAddress;
    
    @Embedded
    @AttributeOverride(name = "zipCode", column = @Column(name = "company_zipCode"))
    @AttributeOverride(name = "address1", column = @Column(name = "company_address1"))
    @AttributeOverride(name = "address2", column = @Column(name = "company_address2"))
    private Address companyAddress;
    // ##### Step3. @Embedded, @Embeddable 사용 할 다중 Class End #####    
    

}
