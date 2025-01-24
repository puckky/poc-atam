package com.atam.vivalibre.dto;

import lombok.Data;

@Data
public class Book {

	private Long id;
	private String title;
	private Integer pages;
	private String summary;
	private AuthorDto author;
	private Long publicationTimestamp;
}
