package com.microservices.payment.payment;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

	  public Payment toPayment(PaymentRequest request) {
		    if (request == null) {
		      return null;
		    }
		    return Payment.builder()
		        .id(request.id())
		        .paymentMethod(request.paymentMethod())
		        .amount(request.amount())
		        .orderId(request.orderId())
		        .createdDate(LocalDateTime.now())
		        .build();
		  }
}
