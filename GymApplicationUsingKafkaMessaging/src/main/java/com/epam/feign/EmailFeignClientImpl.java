package com.epam.feign;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.dto.EmailDto;
@Service
public class EmailFeignClientImpl implements EmailFeignClient {

	@Override
	public ResponseEntity<Void> sendMail(EmailDto emailDto) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
