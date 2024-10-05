package com.microservices.order.utils;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.order.payload.CustomerReponse;

@FeignClient(name = "stores",
				url= "${application.config.customer-url}")
public interface CustomerClient {
	
	@GetMapping("/{id}")
	Optional<CustomerReponse> getCustomerById(@PathVariable("id") String customerId);
}
