package com.springboot.lombok.model;

import javax.persistence.Entity;

@Entity
public class CommerceAlbum extends ItemEntity{

    private String artist;
    private String etc;

}
