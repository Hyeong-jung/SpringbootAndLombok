package com.springboot.lombok.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.lombok.exception.BookNotFoundException;
import com.springboot.lombok.model.Book;
import com.springboot.lombok.service.BookService;

import lombok.extern.slf4j.Slf4j;


// Lombok 로거 필드 생성
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class BookController {

    @Autowired
    BookService bookService;

    // URL - http://localhost:8000/api/books
    @GetMapping(value = "books")
    public ResponseEntity<List<Book>> getBooks() {
        log.info(" books DB 호출");
        final Iterable<Book> bookIterable = bookService.getAllBooks();
        final List<Book> books = StreamSupport.stream(bookIterable.spliterator(), false).collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // URL - http://localhost:8000/api/book/id/1
    @GetMapping(value = "book/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") final int bookId) {
        log.info("books DB 에서 id값으로 book 데이터 호출", bookId);
        final Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id= " + bookId + "not found in the dB."));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // URL - http://localhost:8000/api/book/genre/Mythopoeia
    @GetMapping(value = "book/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable(name = "genre") final String genre) {
        log.info("books DB 에서 genre값으로 book 데이터 호출", genre);
        final List<Book> books = bookService.getAllBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // URL - http://localhost:8000/api/book/quantity/52
    @GetMapping(value = "book/quantity/{quantity}")
    public ResponseEntity<List<Book>> getBooksByQuantityGreaterThanEqual(
            @PathVariable(name = "quantity") final int quantity) {
        log.info("books DB 에서 quantity값으로 >= quantity 조건의 book 데이터 호출.", quantity);
        final List<Book> books = bookService.getAllBooksByQuantityGreaterThanEqual(quantity);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // URL - http://localhost:8000/api/book/save
    // Sample HTTP POST request body.
    /*
    {
        "author": "Vasdev Mohi",
        "genre": "Ghazals",
        "publisher": "Central Sahitya Akademi",
        "title": "Cheque book",
        "quantity": 1,
        "publishedOn": "2020-09-11T11:11:36Z"
    }
    */
    @PostMapping(value = "book/save")
    public ResponseEntity<Void> save(@RequestBody final Book book) {
        log.info("DB 에 book 데이터 저장", book.toString());
        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    // URL - http://localhost:8000/api/book/apiCalㅣ
    // HttpEntity / ResponseEntity 를 이용한 REST API 호출 
    @GetMapping(value = "book/apiCall")
    public ResponseEntity<String> apiCall() {
    	
    	RestTemplate rest = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Content-Type", "application/json");
    	
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("{\n");
    	sb.append("  \"author\": \"author\",\n");
    	sb.append("  \"genre\": \"genre\",\n");
    	sb.append("  \"publisher\": \"publisher\",\n");
    	sb.append("  \"title\": \"title\",\n");
    	sb.append("  \"quantity\": 100\n");
    	sb.append("}");
    	String body = sb.toString();
    	
    	
    	HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
    	ResponseEntity<String> responseEntity = rest.exchange("http://localhost:10093/api/book/save", HttpMethod.POST, requestEntity, String.class);
    	HttpStatus httpStatus = responseEntity.getStatusCode();
    	int status = httpStatus.value();
    	String response = responseEntity.getBody();
    	
    	
    	String result = "Response status: " + status + " || " + response;
    	
    	//System.out.println("Response status: " + status);
    	//System.out.println(response);    	

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    // 마이바티스 리스트 출력1
    // URL - http://localhost:8000/api/book/booksbymapper
    @GetMapping(value = "book/booksbymapper")
    public ResponseEntity<List<Book>> getBooksByMapper() {
        log.info("DB books 리스트 출력");
        final Iterable<Book> bookIterable = bookService.selectBook();
        final List<Book> books = StreamSupport.stream(bookIterable.spliterator(), false).collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    
    // 마이바티스 사용저장1
    //URL - http://localhost:8000/api/book/savebymapper
    @PostMapping(value = "book/savebymapper")
    public ResponseEntity<Void> saveByMapper(@RequestBody final Book book) {
        log.info("DB 에 book 데이터 저장", book.toString());
        
        Map<String, String> map = new HashMap<String, String>();
        // (#{author}, #{genre}, #{published}, #{publisher}, #{quantity}, #{title})
        map.put("author", book.getAuthor());
        map.put("genre", book.getGenre());
        map.put("published", book.getPublishedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));        
        map.put("publisher",book.getPublisher());
        map.put("quantity", Integer.toString(book.getQuantity()));
        map.put("title", book.getTitle());
        
        bookService.insertBookByMapper(map);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
    
    // 마이바티스 사용저장2
    //URL - http://localhost:8000/api/book/savebymapper2   
    @PostMapping(value = "book/savebymapper2")
    public ResponseEntity<Void> savebymapper2(@RequestBody HashMap<String, String> paramMap) {
        bookService.insertBookByMapper(paramMap);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
    @GetMapping(value = "booksResultMap")
    public ResponseEntity<List<Map<String, Object>>> getBooksResultMap() {
        log.info("Getting all books from the dB.");
        
        final Iterable<Map<String, Object>> bookIterable = bookService.selectBookResultMap();
        final List<Map<String, Object>> books = StreamSupport.stream(bookIterable.spliterator(), false).collect(Collectors.toList());        
        
        //return new ResponseEntity<>(bookService.selectBookResultMap(), HttpStatus.OK);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }    
    
    // 마이바티스 리스트 출력2
    // URL - http://localhost:8000/api/book/booksResultMap
    @GetMapping(value = "booksResultMap2")
    public ResponseEntity<Iterable<Map<String, Object>>> getBooksResultMap2() {
        log.info("Getting all books from the dB.");
        
        return new ResponseEntity<>(bookService.selectBookResultMap(), HttpStatus.OK);
    }      
    
}
