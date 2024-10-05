package com.microservices.order.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.order.payload.OrderRequest;
import com.microservices.order.payload.OrderResponse;
import com.microservices.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createProduct(@RequestBody @Valid OrderRequest request){
		return ResponseEntity.ok(orderService.createOrder(request));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<OrderResponse>> findAllOrders(){
		return ResponseEntity.ok(orderService.findAll());
	}
	
	@GetMapping("/{order-id}")
	public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId){
		return ResponseEntity.ok(orderService.findById(orderId));
	}
}
