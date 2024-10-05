package com.microservices.order.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.order.payload.OrderLineResponse;
import com.microservices.order.payload.OrderResponse;
import com.microservices.order.service.OrderLineService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/order-lines")
@RestController
@RequiredArgsConstructor
public class OrderlineController {
	
	private final OrderLineService orderlineService;

//	@GetMapping("/")
//	public ResponseEntity<List<OrderResponse>> findAllOrders(){
//		return ResponseEntity.ok(orderService.findAll());
//	}
	
	@GetMapping("order/{order-id}")
	public ResponseEntity<List<OrderLineResponse>> findById(@PathVariable("order-id") Integer orderId){
		return ResponseEntity.ok(orderlineService.findAllById(orderId));
	}
}
