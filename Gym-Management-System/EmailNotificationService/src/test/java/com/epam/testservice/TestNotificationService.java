package com.epam.testservice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;

import com.epam.dto.EmailDto;
import com.epam.model.Email;
import com.epam.repository.EmailRepository;
import com.epam.service.NotificationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestNotificationService {

	@InjectMocks
	private NotificationServiceImpl notificationService;
	@Mock
	private JavaMailSender mailSender;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private EmailRepository emailRepository;

	@Test
	void testSendEmail() {
		EmailDto emailDto = new EmailDto();
		Email email = new Email();
		when(modelMapper.map(emailDto, Email.class)).thenReturn(email);
		notificationService.sendMail(emailDto);
		verify(emailRepository, times(1)).insert(email);
	}
}
