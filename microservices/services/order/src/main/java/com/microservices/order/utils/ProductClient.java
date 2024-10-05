package com.microservices.order.utils;

import java.util.List;

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
import com.microservices.order.payload.PurchaseRequest;
import com.microservices.order.payload.PurchaseResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductClient {
	
	@Value("${application.config.product-url}")
	private String productUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request){
		HttpHeaders headers = new HttpHeaders();
		headers.set("CONTENT_TYPE", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(request, headers);
		
		ParameterizedTypeReference<List<PurchaseResponse>> responseEntity = new ParameterizedTypeReference<List<PurchaseResponse>>() {
		};
		ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(productUrl+"/purchase", HttpMethod.POST, requestEntity, responseEntity);
		
		if (response.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + response.getStatusCode());
        }
        return  response.getBody();	
	}

}
