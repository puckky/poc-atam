package com.atam.vivalibre.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atam.vivalibre.dto.Book;
import com.atam.vivalibre.dto.BookDate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class BookService {

	private List<Book> books;

	@PostConstruct
	private void leerLibros() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {
		};
		InputStream inputStream = getClass().getResourceAsStream("/books.json");

		try {
			books = mapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar los libros desde el archivo JSON", e);
		}
	}

	public List<BookDate> getFiterBooks(String filtro) {
		return filter(filtro, books);
	}

	private List<BookDate> filter(String filter, List<Book> books) {

		return books.stream().filter(book -> {
			if (book.getPublicationTimestamp() == null) {
				System.out.println("El libro " + book.getTitle() + " sin fecha de publicacion");
				return false;
			} else {
				return true;
			}
		}).filter(book -> book.getTitle().contains(filter) || book.getSummary().contains(filter)
				|| book.getAuthor().getBio().contains(filter))
				.sorted(Comparator.comparing(Book::getPublicationTimestamp)
						.thenComparing(b -> b.getAuthor().getBio().length())).map(b -> new BookDate(b, Instant.ofEpochMilli(b.getPublicationTimestamp())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()))
				.toList();


	}
}
