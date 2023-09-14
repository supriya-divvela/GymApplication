package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.service.TokenService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/gym")
@RestController
public class AuthController {

	@Autowired
	private TokenService tokenService;

	@PostMapping("/token")
	public String token(Authentication authentication) {
		log.debug("Token requested for user: '{}'", authentication.getName());
		String token = tokenService.generateToken(authentication);
		log.debug("Token granted: {}", token);
		return token;
	}
}