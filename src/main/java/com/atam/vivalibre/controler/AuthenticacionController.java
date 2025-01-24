package com.atam.vivalibre.controler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atam.vivalibre.dto.TokenDto;
import com.atam.vivalibre.service.ClienteRest;

@RestController
public class AuthenticacionController {

	@Autowired
	private ClienteRest clienteRest;

	@GetMapping("/get-token")
	public TokenDto recuperaToken() {
		try {
			TokenDto token = clienteRest.getToken();
			token.setFecha(LocalDate.now());
			return token;
		} catch (RuntimeException e) {
			throw new RuntimeException(
					"no se puede conectar contra el servicio, si se estan dentro de un docker no hay visilibad contra otro docker");
		}
	}
}
