package com.epam.service.interfaces;

import com.epam.dto.Credentials;
import com.epam.dto.request.ChangeLoginRequest;

public interface LoginService {
	
	boolean verifyUser(Credentials credentials);

	void changePassword(ChangeLoginRequest changeLoginRequest);
}
