package com.microservices.order.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.microservices.order.payload.CustomerReponse;
import com.microservices.order.payload.PurchaseRequest;
import com.microservices.order.payload.PurchaseResponse;
import com.microservices.order.utils.PaymentMethod;

public record OrderConfirmation(
		String orderRefernce,
		BigDecimal totalAmount,
		PaymentMethod paymentMethod,
		CustomerReponse customer,
		List<PurchaseResponse> products
		) {
}
