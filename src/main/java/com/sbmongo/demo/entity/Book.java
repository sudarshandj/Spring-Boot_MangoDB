package com.sbmongo.demo.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
@Document// Map entity class objects to JSON formatted Documents
public class Book {
	
	@org.springframework.data.annotation.Id
	private String id;
	@NonNull
	private Integer bookId;
	@NonNull
	private String bookName;
	@NonNull
	private String bookAuthor;
	@NonNull
	private Double bookCost;
}
