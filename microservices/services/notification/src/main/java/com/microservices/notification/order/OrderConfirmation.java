package com.microservices.notification.order;

import java.math.BigDecimal;
import java.util.List;

import com.microservices.notification.payment.PaymentMethod;

public record OrderConfirmation(
		String orderRefernce,
		BigDecimal totalAmount,
		PaymentMethod paymentMethod,
		Customer customer,
		List<Product> products
		) {

}
