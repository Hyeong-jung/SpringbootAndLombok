package com.springboot.lombok.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	//// ##### Step1. @EmbeddedId 사용 할 멤버 Start #####
	//@Column(name = "user_id")
	//private String accessToken;
	////##### Step1. @EmbeddedId 사용 할 멤버 End #####
	
	
	//##### Step2. @EmbeddedId 사용 Class Start #####
    @EmbeddedId
    private UserId accessToken;
	//##### Step2. @EmbeddedId 사용 Class End #####
	
    
	private String name;
	
}
