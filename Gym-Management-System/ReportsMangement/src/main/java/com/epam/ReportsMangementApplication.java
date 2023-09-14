package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportsMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsMangementApplication.class, args);
	}

	@Bean
	public ModelMapper getModeMapper() {
		return new ModelMapper();
	}
}
