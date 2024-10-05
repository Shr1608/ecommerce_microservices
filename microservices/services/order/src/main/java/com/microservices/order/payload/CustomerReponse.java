package com.microservices.order.payload;

public record CustomerReponse (
		String id,
		String firstName,
		String lastName,
		String email
		){
}
