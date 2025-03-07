package com.microservices.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.order.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

}
