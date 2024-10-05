package com.microservices.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.order.payload.OrderLineRequest;
import com.microservices.order.payload.OrderLineResponse;
import com.microservices.order.repository.OrderLineRepo;
import com.microservices.order.utils.OrderLineMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {
	
	private final OrderLineRepo orderLineRepo;
	
	private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepo.save(order).getId();
    }

	public List<OrderLineResponse> findAllById(Integer orderId) {
		// TODO Auto-generated method stub
		return orderLineRepo.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).toList();
	}
	
}
