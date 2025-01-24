package com.atam.vivalibre.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDate {
	
 private Book book;
 
 @JsonFormat(pattern = "MM-dd-yyyy")
 private LocalDate date;
}
