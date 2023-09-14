package com.epam.utilities;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPasswordGenerator {
	private RandomPasswordGenerator() {
	}

	public static String generateRandomPassword() {
		char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&+=")
				.toCharArray();
		return RandomStringUtils.random(10, 0, possibleCharacters.length - 1, false, false, possibleCharacters,
				new SecureRandom());
	}
}
