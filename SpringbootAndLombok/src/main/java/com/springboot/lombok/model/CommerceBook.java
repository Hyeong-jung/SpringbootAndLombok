package com.springboot.lombok.model;

import javax.persistence.Entity;

@Entity
public class CommerceBook extends ItemEntity{
    private String author;
    private String isbn;
}
