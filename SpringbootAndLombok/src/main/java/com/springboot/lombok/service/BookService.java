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
	
    @Autowired
    BookRepository bookRepository;
    
	@Autowired
	public BookMapper mapper;


    public void save(final Book book) {
        bookRepository.save(book);
    }

    public long getBooksCount() {
        return bookRepository.count();
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(final int bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getAllBooksByGenre(final String genre) {
        return bookRepository.findBookByGenre(genre);
    }

    public List<Book> getAllBooksByQuantityGreaterThanEqual(final int quantity) {
        return bookRepository.findBookByQuantityGreaterThanEqual(quantity);
    }
    
    
	public List<Book> selectBook() {
		
			
		
		return mapper.selectBook(); 
	}
	
	
    public void insertBookByMapper(Map<String, String> map) {
    	mapper.insertBookByMapper(map); 
    }	
    
    
	//public List<Map<String, String>> selectBookResultMap() {		
    //return mapper.selectBookResultMap(); 
    //}
	
	public List<Map<String, Object>> selectBookResultMap() {		
		return mapper.selectBookResultMap(); 
	}    	
}
