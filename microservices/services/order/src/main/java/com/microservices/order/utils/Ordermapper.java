package com.microservices.order.utils;

import org.springframework.stereotype.Service;

import com.microservices.order.Order;
import com.microservices.order.payload.OrderRequest;
import com.microservices.order.payload.OrderResponse;

@Service
public class Ordermapper {

	 public Order toOrder(OrderRequest request) {
		    if (request == null) {
		      return null;
		    }
		    return Order.builder()
		        .id(request.id())
		        .reference(request.reference())
		        .paymentMethod(request.paymentMethod())
		        .customerId(request.customerId())
		        .build();
		  }

	 public OrderResponse fromOrder(Order order) {
		    return new OrderResponse(
		        order.getId(),
		        order.getReference(),
		        order.getTotalAmount(),
		        order.getPaymentMethod(),
		        order.getCustomerId()
		    );
		  }
	
}
