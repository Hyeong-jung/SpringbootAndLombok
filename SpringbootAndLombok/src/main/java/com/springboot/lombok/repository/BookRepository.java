package com.springboot.lombok.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.lombok.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findBookByGenre(String genre);

    List<Book> findBookByQuantityGreaterThanEqual(int quantity);
}
