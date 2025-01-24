package com.atam.vivalibre.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.atam.vivalibre.dto.TokenDto;

@Component
public class ClienteRest {

	@Autowired
	private RestTemplate restTemplate;
	
	public TokenDto getToken() {
		
        Map<String, String> data = new HashMap<>();
        data.put("username", "auth-vivelibre");
        data.put("password", "password");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		 HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);
		
		// Esto dentro de un contenedor docker no funcion para acceder al contenedor del token
		return restTemplate.postForEntity("http://localhost:8081/token", request, TokenDto.class, headers).getBody();
	}
}
