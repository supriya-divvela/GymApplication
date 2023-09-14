package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.LoginController;
import com.epam.dto.Credentials;
import com.epam.dto.request.ChangeLoginRequest;
import com.epam.exceptions.LoginException;
import com.epam.exceptions.UserException;
import com.epam.service.interfaces.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoginController.class)
class TestLoginController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginService loginService;

	@Test
	void testVerifyUser() throws Exception {
		Credentials credentials = new Credentials("user", "1234");
		when(loginService.verifyUser(credentials)).thenReturn(true);
		mockMvc.perform(post("/gym/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(credentials))).andExpect(status().isOk());
	}
	
	@Test
	void testVerifyUserWithUserException() throws Exception {
		Credentials credentials = new Credentials("user", "1234");
		when(loginService.verifyUser(credentials)).thenThrow(UserException.class);
		mockMvc.perform(post("/gym/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(credentials))).andExpect(status().isOk());
	}
	
	@Test
	void testVerifyUserWithLoginException() throws Exception {
		Credentials credentials = new Credentials("user", "1234");
		when(loginService.verifyUser(credentials)).thenThrow(LoginException.class);
		mockMvc.perform(post("/gym/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(credentials))).andExpect(status().isOk());
	}

	@Test
	void testVerifyUserWithInvalidDetails() throws Exception {
		Credentials credentials = new Credentials("user", "1234");
		when(loginService.verifyUser(credentials)).thenReturn(false);
		mockMvc.perform(post("/gym/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(credentials))).andExpect(status().isBadRequest());
	}

	@Test
	void testChangePassword() throws Exception {
		ChangeLoginRequest changeLoginRequest = new ChangeLoginRequest();
		doNothing().when(loginService).changePassword(null);
		mockMvc.perform(post("/gym/login/changepassword").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(changeLoginRequest))).andExpect(status().isOk());
	}
}
