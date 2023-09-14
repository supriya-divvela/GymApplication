package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.EmailDto;
import com.epam.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/email")
@RestController
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResponseEntity<Void> sendSimpleMailMessage(@RequestBody EmailDto emailDto) {
		log.info("NotificationController : Send Email Method..");
		notificationService.sendMail(emailDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
