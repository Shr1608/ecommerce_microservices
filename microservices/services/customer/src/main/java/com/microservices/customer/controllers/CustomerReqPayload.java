package com.microservices.customer.controllers;

import com.microservices.customer.utils.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerReqPayload (
	String id,
	@NotNull(message = "Customer firstname is requied.")
	String firstName,
	@NotNull(message = "Customer lastname is requied.")
	String lastName,
	@NotNull(message = "Customer email is requied.")
	@Email(message = "customer Email is not valid email.")
	String email,
	Address address
) {}
