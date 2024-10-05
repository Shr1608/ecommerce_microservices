package com.microservices.customer.utils;

public record CustomerReponse (
	String id,
	String firstName,
	String lastName,
	String email,
	Address address
) {}
