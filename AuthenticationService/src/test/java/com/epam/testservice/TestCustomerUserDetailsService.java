package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.CustomUserDetailsService;

@ExtendWith(MockitoExtension.class)
class TestCustomerUserDetailsService {

	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;
	@Mock
	private UserRepository userRepository;

	@Test
	void testLoadUserByUsername() {
		Optional<User> user = Optional.ofNullable(new User());
		when(userRepository.findByUsername("supriya")).thenReturn(user);
		assertEquals(user.get().toString(), customUserDetailsService.loadUserByUsername("supriya").toString());
	}

	@Test
	void testLoadUserByUsernameWithInvalidUser() {
		when(userRepository.findByUsername("supriya")).thenReturn(Optional.empty());
		assertThrows(UsernameNotFoundException.class,()->customUserDetailsService.loadUserByUsername("supriya"));

	}
}
