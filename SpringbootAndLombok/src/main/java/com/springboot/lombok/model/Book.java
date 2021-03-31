package com.springboot.lombok.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
@Table(name = "book")
// Lombok annotations
// Causes lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go.
@Data
// Causes lombok to implement the Builder design pattern for the Pojo class.
// Usage can be seen in DefaultBookLoader.java -> createNewBook() method.
@Builder
// Causes lombok to generate a constructor with no parameters.
@NoArgsConstructor
// // Causes lombok to generate a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
// Spring framework annotation
@Component
public class Book {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MariaDB
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator") // Oracle DB
    //@SequenceGenerator(name="book_generator", sequenceName = "book_seq", allocationSize=50) // Oracle DB
    
    int id;
    @Column(name = "author", nullable = false)
    String author;
    @Column(name = "genre")
    String genre;
    @Column(name = "publisher", nullable = false)
    String publisher;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "quantity")
    int quantity;
    @Column(name = "published_on")
    LocalDateTime publishedOn;
}
