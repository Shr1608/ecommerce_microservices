package com.microservices.customer.utils;

import org.springframework.stereotype.Component;

import com.microservices.customer.controllers.CustomerReqPayload;
import com.microservices.customer.entity.Customer;

@Component
public class CustomerMapper {

	public Customer toCustomer(CustomerReqPayload payload) {
		if(payload == null)
			return null;
		
		return Customer.builder()
				.id(payload.id())
				.firstName(payload.firstName())
				.lastName(payload.lastName())
				.email(payload.email())
				.address(payload.address())
				.build();
	}

	public CustomerReponse fromCustomer(Customer customer) {
		return new CustomerReponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress());
	}

}
