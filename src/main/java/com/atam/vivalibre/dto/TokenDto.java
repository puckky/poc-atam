package com.atam.vivalibre.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TokenDto {

	private String token;

	@JsonFormat(pattern = "MMMM dd,yyyy")
	private LocalDate fecha;
}
