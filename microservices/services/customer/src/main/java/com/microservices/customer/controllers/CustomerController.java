package com.microservices.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import com.microservices.customer.entity.Customer;
import com.microservices.customer.services.CustomerService;
import com.microservices.customer.utils.CustomerReponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; 
	
	@PostMapping("/create")
	public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerReqPayload payload){
		String customerId = customerService.createCustomer(payload);
		return new ResponseEntity<String>(customerId, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerReqPayload payload){
		Customer customer = customerService.updateCustomer(payload);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CustomerReponse>> getAllCustomer(){
		List<CustomerReponse> customer = customerService.getAllCustomer();
		return new ResponseEntity<List<CustomerReponse>>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> isExistsCustomer(@PathVariable("id") String customerId){
		return new ResponseEntity<Boolean>(customerService.isExists(customerId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerReponse> getCustomerById(@PathVariable("id") String customerId){
		return new ResponseEntity<CustomerReponse>(customerService.findById(customerId), HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deketeCustomerById(@PathVariable("id") String customerId){
		return new ResponseEntity<String>(customerService.deleteCustomerById(customerId), HttpStatus.OK);
	}
}
