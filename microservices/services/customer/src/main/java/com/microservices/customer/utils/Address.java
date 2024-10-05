package com.microservices.customer.utils;

import org.springframework.validation.annotation.Validated;

import com.microservices.customer.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Validated
public class Address {

	private String houseNo;
	private String street;
	private String city;
	private String state;
	private String zip;
	
}
