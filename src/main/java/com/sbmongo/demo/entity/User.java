package com.sbmongo.demo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
	private String id;
	
	@Indexed
	private String ic;
	
	private String name;
	
	private int age;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;
	
	public User(String ic, String name, int age, Date createdDate) {
		super();
		this.ic = ic;
		this.name = name;
		this.age = age;
		this.createdDate = createdDate;
	}

	
}
