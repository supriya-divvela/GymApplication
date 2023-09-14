package com.epam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.Credentials;
import com.epam.dto.request.ChangeLoginRequest;
import com.epam.service.interfaces.LoginService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/gym/login")
@AllArgsConstructor
@RestController
public class LoginController {
	private LoginService loginService;

	@PostMapping
	public ResponseEntity<Void> verifyUser(@RequestBody @Valid Credentials credentials) {
		log.info("LoginController : verifyUser ");
		if (loginService.verifyUser(credentials)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/changepassword")
	public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangeLoginRequest changeLoginRequest) {
		log.info("LoginController : changePassword ");
		loginService.changePassword(changeLoginRequest);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
