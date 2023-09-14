package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.service.AuthService;
import com.epam.service.JwtService;

@ExtendWith(MockitoExtension.class)
class TestAuthService {
	@InjectMocks
	private AuthService authService;
	@Mock
	private JwtService jwtService;

	@Test
	void testGenerateToken() {
		when(jwtService.generateToken("supriya")).thenReturn("supriya");
		assertEquals("supriya",authService.generateToken("supriya"));
	}

	@Test
	void testValidateToken() {
		doNothing().when(jwtService).validateToken("supriya");
		authService.validateToken("supriya");
		verify(jwtService, times(1)).validateToken("supriya");
	}

}
