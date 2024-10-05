package com.microservices.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.order.payload.OrderLineResponse;
import com.microservices.order.utils.OrderLine;

@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine, Integer>{

	List<OrderLine> findAllByOrderId(Integer orderId);

}
