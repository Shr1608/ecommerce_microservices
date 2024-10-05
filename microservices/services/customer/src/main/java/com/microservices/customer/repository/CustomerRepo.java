package com.microservices.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.customer.entity.Customer;

public interface CustomerRepo extends MongoRepository<Customer, String> {

}
