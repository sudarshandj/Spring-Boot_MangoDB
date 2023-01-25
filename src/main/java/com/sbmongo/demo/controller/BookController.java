package com.sbmongo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/book/{bookId}")
	public Book getBookById(@PathVariable Integer bookId) {
		return bookRepository.findByBookId(bookId);
	}
	
	@DeleteMapping("/book/{bookId}")
	public String deleteBookById(@PathVariable String bookId) {
		bookRepository.deleteById(bookId);
		return "Book deleted successfully!!!";
	}
	
	/*@PutMapping("/book/{bookId}")
	public ResponseEntity<String> updateBookById(@RequestBody Book book) {
		bookRepository.insert(entity)
		return new ResponseEntity<String>("Book for {bookId} is updated..", HttpStatus.OK);
	}*/
}
