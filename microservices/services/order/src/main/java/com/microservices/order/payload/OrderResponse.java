package com.microservices.order.payload;

import java.math.BigDecimal;

import com.microservices.order.utils.PaymentMethod;

public record OrderResponse(
	    Integer id,
	    String reference,
	    BigDecimal amount,
	    PaymentMethod paymentMethod,
	    String customerId
	) {

}
