package com.microservices.notification.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
		String orderRefernce,
		BigDecimal amount,
		PaymentMethod paymentMethod,
		String customerFirstname,
		String customerLastname,
		String customerEmail
		) {

}
