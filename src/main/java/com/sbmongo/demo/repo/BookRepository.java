package com.sbmongo.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sbmongo.demo.entity.Book;

public interface BookRepository extends MongoRepository<Book, String>{
	public Book findByBookId(Integer bookId);
}
