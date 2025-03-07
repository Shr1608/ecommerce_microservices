package com.microservices.order.utils;

import org.springframework.stereotype.Service;

import com.microservices.order.Order;
import com.microservices.order.payload.OrderLineRequest;
import com.microservices.order.payload.OrderLineResponse;

@Service
public class OrderLineMapper {

	public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
