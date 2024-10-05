package com.microservices.customer.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customer.controllers.CustomerReqPayload;
import com.microservices.customer.entity.Customer;
import com.microservices.customer.exceptions.CustomerNotFoundException;
import com.microservices.customer.repository.CustomerRepo;
import com.microservices.customer.utils.Address;
import com.microservices.customer.utils.CustomerMapper;
import com.microservices.customer.utils.CustomerReponse;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CustomerMapper mapper;

	public String createCustomer(CustomerReqPayload payload) {
		var customer = customerRepo.save(mapper.toCustomer(payload));
		return customer.getId();
	}

	public Customer updateCustomer(@Valid CustomerReqPayload payload) {
		var customer = customerRepo.findById(payload.id())
				.orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update customer:: No customer found with Id: %s",payload.id())));
		var updatedCustomer = mergeCustomer(customer, payload);
		System.err.println(updatedCustomer);
		var changedcustomer = customerRepo.save(updatedCustomer);
		return changedcustomer;
	}

	private Customer mergeCustomer(Customer customer, @Valid CustomerReqPayload payload) {
		if(StringUtils.isNotBlank(payload.id())) {
			customer.setId(payload.id());
		}
		if(StringUtils.isNotBlank(payload.firstName())) {
			customer.setFirstName(payload.firstName());
		}
		if(StringUtils.isNotBlank(payload.lastName())) {
			customer.setLastName(payload.lastName());
		}
		if(StringUtils.isNotBlank(payload.email())) {
			customer.setEmail(payload.email());
		}
		if(payload.address() != null) {
			customer.setAddress(payload.address());
		}
		return customer;
	}

	public List<CustomerReponse> getAllCustomer() {
		List<Customer> customers = customerRepo.findAll();
		List<CustomerReponse> customersRes = customers.stream().map((customer)->mapper.fromCustomer(customer)).collect(Collectors.toList());
		return customersRes;
	}

	public Boolean isExists(String customerId) {
		return customerRepo.findById(customerId).isPresent();
	}

	public CustomerReponse findById(String customerId) {
		// TODO Auto-generated method stub
		return  customerRepo.findById(customerId).map(mapper::fromCustomer)
				.orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update customer:: No customer found with Id: %s",customerId)));
	}

	public String deleteCustomerById(String customerId) {
		customerRepo.deleteById(customerId);
		return "Deleted Successfully";
	}

}
