package com.microservices.order.payload;

import java.math.BigDecimal;

import com.microservices.order.utils.PaymentMethod;


public record PaymentRequest(
	    Integer id,
	    BigDecimal amount,
	    PaymentMethod paymentMethod,
	    Integer orderId,
	    String orderReference,
	    CustomerReponse customer
	) {

}
