package com.epam.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.EmailDto;

@FeignClient( name = "email-service/email",fallback = EmailFeignClientImpl.class)
@LoadBalancerClient(name="email-service/email",configuration = EmailFeignClientImpl.class)
public interface EmailFeignClient {
	@PostMapping
	public ResponseEntity<Void> sendMail(@RequestBody EmailDto emailDto) ;

}
