package com.microservices.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.payload.ProductPurchaseRequest;
import com.microservices.product.payload.ProductPurchaseResponse;
import com.microservices.product.payload.ProductResponse;
import com.microservices.product.payload.ProductResquest;
import com.microservices.product.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductResquest request){
		return ResponseEntity.ok(service.createProduct(request));
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> request){
		System.err.println(request);
		return ResponseEntity.ok(service.purchaseProduct(request));
	}
	
	@GetMapping("/{product-id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("product-id") Integer productId){
		return ResponseEntity.ok(service.findProductById(productId));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductResponse>> getProductById(){
		return ResponseEntity.ok(service.findProduct());
	}
	
}
