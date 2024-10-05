package com.microservices.order.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.order.exceptions.BusinessException;
import com.microservices.order.payload.PaymentRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentClient {

	@Value("${application.config.payment-url}")
	private String paymentUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	 public Integer createPayment(PaymentRequest request){
		 HttpHeaders headers = new HttpHeaders();
		 headers.set("CONTENT_TYPE", MediaType.APPLICATION_JSON_VALUE);
		 
		 HttpEntity<PaymentRequest> requestEntity = new HttpEntity<PaymentRequest>(request, headers);
		 System.err.println(requestEntity);
		 ParameterizedTypeReference<Integer> responseEntity = new ParameterizedTypeReference<Integer>() {
		};
		
		ResponseEntity<Integer> response = restTemplate.exchange(paymentUrl+"/create", HttpMethod.POST, requestEntity, responseEntity);
		
		if (response.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing payment: " + response.getStatusCode());
        }
        return  response.getBody();
				
	 }
}
