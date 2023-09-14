package com.epam.service;

import com.epam.dto.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface NotificationService {
	void sendMail(String emailDto) throws JsonProcessingException;

}
