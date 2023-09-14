package com.epam.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.dto.TrainingSummaryDto;

import jakarta.servlet.http.HttpServletResponse;

@FeignClient( name = "reports-service/training-summary",fallback = ReportsClientImpl.class)
@LoadBalancerClient(name="reports-service/training-summary",configuration = ReportsClientImpl.class)
public interface ReportsClient {
	 
	@PostMapping
	public ResponseEntity<Void> addTrainingSummary(@RequestBody TrainingSummaryDto trainingSummaryDto);
		

	@GetMapping
	public ResponseEntity<Void> getAllTrainings(@RequestParam("trainerUsername") String trainerUsername,HttpServletResponse response);

}
