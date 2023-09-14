package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.Credentials;
import com.epam.service.AuthService;
@RequestMapping("/auth")
@RestController
public class AuthController {
	@Autowired
	private AuthService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public String getToken(@RequestBody Credentials credentials) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
		if (authenticate.isAuthenticated()) {
			return service.generateToken(credentials.getUsername());
		} else {
			throw new RuntimeException("invalid access");
		}
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return "Token is valid";
	}
}
