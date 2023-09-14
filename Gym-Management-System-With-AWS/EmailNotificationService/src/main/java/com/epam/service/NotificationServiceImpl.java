package com.epam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.epam.dto.EmailDto;
import com.epam.model.Email;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
	private final JavaMailSender mailSender;
	private final ModelMapper modelMapper;
	private final DynamoDbTemplate dynamoDbTemplate;

	@Value("${spring.mail.username}")
	private String emailFrom;

	@Override
	@SqsListener(value = "email-queue")
	public void sendMail(String emailMessage) throws JsonProcessingException {
		log.info("NotificationServiceImpl : sendMail");
		EmailDto emailDto=new ObjectMapper().readValue(emailMessage,EmailDto.class);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(emailDto.getToEmail());
		message.setCc(emailDto.getCcEmail());
		message.setText(emailDto.getBody());
		message.setSubject(emailDto.getSubject());
		mailSender.send(message);
		emailDto.setStatus("sent");
		Email email=modelMapper.map(emailDto, Email.class);
		email.setId(UUID.randomUUID());
		dynamoDbTemplate.save(email);
	}

}
