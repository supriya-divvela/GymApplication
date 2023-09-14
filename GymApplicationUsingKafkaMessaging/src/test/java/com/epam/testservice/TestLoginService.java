package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.dto.Credentials;
import com.epam.dto.request.ChangeLoginRequest;
import com.epam.exceptions.LoginException;
import com.epam.exceptions.UserException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.impl.LoginServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestLoginService {

	@InjectMocks
	private LoginServiceImpl loginService;

	@Mock
	private UserRepository userRepository;

	@Test
	void testVerifyUser() {
		Credentials credentials = new Credentials("supriya", "1234");
		when(userRepository.existsByUsername(credentials.getUsername())).thenReturn(true);
		when(userRepository.existsByUsernameAndPassword("supriya", "1234")).thenReturn(true);
		assertTrue(loginService.verifyUser(credentials));
	}

	@Test
	void testVerifyUserWithWrongPassword() {
		Credentials credentials = new Credentials("supriya", "1234");
		when(userRepository.existsByUsername(credentials.getUsername())).thenReturn(true);
		when(userRepository.existsByUsernameAndPassword("supriya", "1234")).thenReturn(false);
		assertFalse(loginService.verifyUser(credentials));
	}

	@Test
	void testVerifyUserWithInvalidUser() {
		Credentials credentials = new Credentials("supriya", "1234");
		when(userRepository.existsByUsername(credentials.getUsername())).thenReturn(false);
		assertThrows(LoginException.class, () -> loginService.verifyUser(credentials));
	}
	
	@Test
	void testChangePasswordWithInvalidUser() {
		ChangeLoginRequest changeLoginRequest=new ChangeLoginRequest();
		when(userRepository.existsByUsername(changeLoginRequest.getUsername())).thenReturn(false);
		assertThrows(LoginException.class, () -> loginService.changePassword(changeLoginRequest));
	}
	
	@Test
	void testChangePassword() {
		ChangeLoginRequest changeLoginRequest=new ChangeLoginRequest("supriyadivvela9848","1234","1234");
		Optional<User> user=Optional.ofNullable(new User(1,"supriya","divvela","supriyadivvela9848","1234","supriyadivvela9848@gmail.com",true,LocalDate.now()));
		when(userRepository.existsByUsername(changeLoginRequest.getUsername())).thenReturn(true);
		when(userRepository.findByUsername(changeLoginRequest.getUsername())).thenReturn(user);
		verify(userRepository,times(0)).save(user.get());
		loginService.changePassword(changeLoginRequest);
		verify(userRepository,times(1)).save(user.get());
	}
	
	@Test
	void testChangePasswordWithInvalidPassword() {
		ChangeLoginRequest changeLoginRequest=new ChangeLoginRequest("supriyadivvela9848","1234","123456");
		Optional<User> user=Optional.ofNullable(new User(1,"supriya","divvela","supriyadivvela9848","12345678","supriyadivvela9848@gmail.com",true,LocalDate.now()));
		when(userRepository.existsByUsername(changeLoginRequest.getUsername())).thenReturn(true);
		when(userRepository.findByUsername(changeLoginRequest.getUsername())).thenReturn(user);
		assertThrows(UserException.class, ()->loginService.changePassword(changeLoginRequest));
		verify(userRepository,times(0)).save(user.get());
	}
	
	@Test
	void testChangePasswordWithInvalidUserInUserRepository() {
		ChangeLoginRequest changeLoginRequest=new ChangeLoginRequest("supriyadivvela9848","1234","123456");
		Optional<User> user=Optional.ofNullable(new User());
		when(userRepository.existsByUsername(changeLoginRequest.getUsername())).thenReturn(true);
		when(userRepository.findByUsername(changeLoginRequest.getUsername())).thenReturn(Optional.empty());
		assertThrows(UserException.class, ()->loginService.changePassword(changeLoginRequest));
		verify(userRepository,times(0)).save(user.get());
	}

}
