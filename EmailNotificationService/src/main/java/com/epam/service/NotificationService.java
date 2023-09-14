package com.epam.service;

import com.epam.dto.EmailDto;

public interface NotificationService {
	void sendMail(EmailDto emailDto);

}
