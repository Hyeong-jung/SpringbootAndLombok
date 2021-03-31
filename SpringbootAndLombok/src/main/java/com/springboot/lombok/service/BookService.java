package com.springboot.lombok.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lombok.model.Book;
import com.springboot.lombok.model.TestVo;
import com.springboot.lombok.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

import com.springboot.lombok.mapper.BookMapper;
import com.springboot.lombok.mapper.TestMapper;
import com.springboot.lombok.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Service
public class BookService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// JPA Interface Repository
    @Autowired
    BookRepository bookRepository;
    
    // Mybatis Interface Mapper
	@Autowired
	public BookMapper mapper;

	// JPA Data Save
    public void save(final Book book) {
        bookRepository.save(book);
    }

    // JPA Data Count
    public long getBooksCount() {
        return bookRepository.count();
    }

    // JPA Data List
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // JPA Search Data List By Id
    public Optional<Book> getBookById(final int bookId) {
        return bookRepository.findById(bookId);
    }

    // JPA Search Data List By String genre
    public List<Book> getAllBooksByGenre(final String genre) {
        return bookRepository.findBookByGenre(genre);
    }

    // JPA Search Data List By operator greater than or equal 
    public List<Book> getAllBooksByQuantityGreaterThanEqual(final int quantity) {
        return bookRepository.findBookByQuantityGreaterThanEqual(quantity);
    }
    
    
    // Mybatis Data List
	public List<Book> selectBook() {	
		return mapper.selectBook(); 
	}
	
	// Mybatis Data Save
    public void insertBookByMapper(Map<String, String> map) {
    	mapper.insertBookByMapper(map); 
    }	
    
    
	//public List<Map<String, String>> selectBookResultMap() {		
    //return mapper.selectBookResultMap(); 
    //}
	
    // Mybatis Data List
	public List<Map<String, Object>> selectBookResultMap() {		
		return mapper.selectBookResultMap(); 
	}    	
}
