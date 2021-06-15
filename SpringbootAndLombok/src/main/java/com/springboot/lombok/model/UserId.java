package com.springboot.lombok.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;


@Embeddable
@Getter
public class UserId implements Serializable {

    @Column(name = "user_id")
    private String accessToken;	
	
}
