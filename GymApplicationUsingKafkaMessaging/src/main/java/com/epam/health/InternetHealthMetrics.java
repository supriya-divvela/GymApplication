package com.epam.health;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InternetHealthMetrics implements HealthIndicator {

	@Override
	public Health health() {
		return checkInternet() ? Health.up().withDetail("success code", "Active Internet Connection").build()
				: Health.down().withDetail("error code", "InActive Internet Connection").build();
	}

	public boolean checkInternet() {
		boolean flag = true;
		try {
			URL url = new URL("https://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

}
