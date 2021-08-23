package com.springboot.lombok.model;

import javax.persistence.Entity;

@Entity
public class CommerceMovie extends ItemEntity{
    private String director;
    private String actor;
}
