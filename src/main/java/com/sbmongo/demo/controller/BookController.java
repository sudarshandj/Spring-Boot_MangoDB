package com.sbmongo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbmongo.demo.entity.Book;
import com.sbmongo.demo.repo.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/book")
	public ResponseEntity<String> addBook(@RequestBody Book book){
		bookRepository.save(book);
		return new ResponseEntity<String>("Book Saved", HttpStatus.OK);
	}
	
	@GetMapping("/books")
	public List<Book> books(){
		return bookRepository.findAll();
	}
}
