package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.epam.dto.EmailDto;
import com.epam.model.Email;
import com.epam.repository.EmailRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
	private final JavaMailSender mailSender;
	private final ModelMapper modelMapper;
	private final EmailRepository emailRepository;

	@Value("${spring.mail.username}")
	private String emailFrom;

	@Override
	@KafkaListener(topics = "email-messages", groupId = "emailapp")
	public void sendMail(EmailDto emailDto) {
		log.info("NotificationServiceImpl : sendMail");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(emailDto.getToEmail());
		message.setCc(emailDto.getCcEmail());
		message.setText(emailDto.getBody());
		message.setSubject(emailDto.getSubject());
		mailSender.send(message);
		emailDto.setStatus("sent");
		emailRepository.insert(modelMapper.map(emailDto, Email.class));
	}

}
