package com.epam.service.impl;

import org.springframework.stereotype.Service;

import com.epam.dto.Credentials;
import com.epam.dto.request.ChangeLoginRequest;
import com.epam.exceptions.LoginException;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.interfaces.LoginService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

	private UserRepository userRepository;

	@Override
	public boolean verifyUser(Credentials credentials) {
		log.info("LoginServiceImpl : verifyUserMethod ");
		if (!userRepository.existsByUsername(credentials.getUsername())) {
			throw new LoginException("User doesnot exists..please register..");
		}
		return userRepository.existsByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
	}

	@Override
	public void changePassword(ChangeLoginRequest changeLoginRequest) {
		log.info("LoginServiceImpl : changePasswordMethod ");
		if (!userRepository.existsByUsername(changeLoginRequest.getUsername())) {
			throw new LoginException("User doesnot exists..please register..");
		}
		User user = userRepository.findByUsername(changeLoginRequest.getUsername())
				.orElseThrow(() -> new UserException("User Does Not Exists.."));
		if (!user.getPassword().equals(changeLoginRequest.getOldPassword())) {
			throw new UserException("Old Password and New Password did not match..");
		}
		user.setPassword(changeLoginRequest.getNewPassword());
		userRepository.save(user);
	}

}
