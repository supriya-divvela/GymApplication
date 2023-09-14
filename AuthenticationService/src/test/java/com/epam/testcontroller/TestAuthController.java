package com.epam.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.epam.controller.AuthController;
import com.epam.dto.Credentials;
import com.epam.service.AuthService;

@ExtendWith(MockitoExtension.class)
class TestAuthController {

	@InjectMocks
	private AuthController authcontroller;

	@Mock
	private AuthService service;

	@Mock
	private AuthenticationManager authenticationManager;

	@Test
	void testValidateToken() {
		doNothing().when(service).validateToken("supriya");
		assertEquals("Token is valid", authcontroller.validateToken("supriya"));
	}

	@Test
	void testGetToken() {
		when(authenticationManager.authenticate(any())).thenReturn(new Authentication() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getName() {
				return null;
			}
			
			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				
			}
			
			@Override
			public boolean isAuthenticated() {
				return true;
			}
			
			@Override
			public Object getPrincipal() {
				return null;
			}
			
			@Override
			public Object getDetails() {
				return null;
			}
			
			@Override
			public Object getCredentials() {
				return null;
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}
		});
		assertNull(authcontroller.getToken(new Credentials("supriya","1234")));
	}

	@Test
	void testGetTokenWithInvalidAuthentication() {
		when(authenticationManager.authenticate(any())).thenReturn(new Authentication() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getName() {
				return null;
			}
			
			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				
			}
			
			@Override
			public boolean isAuthenticated() {
				return false;
			}
			
			@Override
			public Object getPrincipal() {
				return null;
			}
			
			@Override
			public Object getDetails() {
				return null;
			}
			
			@Override
			public Object getCredentials() {
				return null;
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}
		});
		assertThrows(RuntimeException.class,()->authcontroller.getToken(new Credentials("supriya","1234")));
	}

}
