package com.atam.vivalibre.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atam.vivalibre.dto.BookDate;
import com.atam.vivalibre.service.BookService;

@RestController
public class BooksController {

	@Autowired
	BookService bookService;

	@GetMapping("/book")
	List<BookDate> getLibros(@RequestParam String filtro) {

		return bookService.getFiterBooks(filtro);
	}
}
