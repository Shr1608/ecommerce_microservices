package com.microservices.payment.notification;

import java.math.BigDecimal;

import com.microservices.payment.payment.PaymentMethod;

public record PaymentNotificationRequest(
		String orderRefernce,
		BigDecimal amount,
		PaymentMethod paymentMethod,
		String customerFirstname,
		String customerLastname,
		String customerEmail
		) {

}
