package com.springboot.lombok.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String zipCode;
    private String address1;
    private String address2;	
	
}
