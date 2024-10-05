package com.microservices.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.microservices.order.exceptions.BusinessException;
import com.microservices.order.kafka.OrderConfirmation;
import com.microservices.order.kafka.OrderProducer;
import com.microservices.order.payload.OrderLineRequest;
import com.microservices.order.payload.OrderRequest;
import com.microservices.order.payload.OrderResponse;
import com.microservices.order.payload.PaymentRequest;
import com.microservices.order.payload.PurchaseRequest;
import com.microservices.order.payload.PurchaseResponse;
import com.microservices.order.repository.OrderRepo;
import com.microservices.order.utils.CustomerClient;
import com.microservices.order.utils.Ordermapper;
import com.microservices.order.utils.PaymentClient;
import com.microservices.order.utils.ProductClient;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class OrderService {
	
	@Autowired
	private CustomerClient customerClient;
	@Autowired
	private ProductClient productClient;
	@Autowired
	private PaymentClient paymentClient;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private Ordermapper ordermapper;
	@Autowired
	private OrderLineService orderLineService;
	@Autowired
	private OrderProducer orderProducer;
	

	public Integer createOrder(@Valid OrderRequest request) {
		// check the customer --> openFiegn
		var customer = this.customerClient.getCustomerById(request.customerId())
				.orElseThrow(() -> new BusinessException("Cannot create order:: No customer found with provided id."));
		
		// purchase the product --> product ms
		List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(request.products());
		
		//persist order
		var order = orderRepo.save(ordermapper.toOrder(request));
		
		//persist order lines
		
		for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
		
		//start payment process
		PaymentRequest paymentRequest = new PaymentRequest(request.id(), 
				request.amount(), request.paymentMethod(), order.getId(), order.getReference(), 
				customer);
		
		paymentClient.createPayment(paymentRequest);
		
		//send order confirmation --> notification-ms (kafka)
		orderProducer.sendOrderConfirmation(
				new OrderConfirmation(request.reference(), request.amount(),
						request.paymentMethod(), customer, purchasedProducts)
				);
		
		return order.getId();
	}


	public List<OrderResponse> findAll() {
		var savedorder = orderRepo.findAll();
		return savedorder.stream().map(ordermapper::fromOrder).toList();
	}


	public OrderResponse findById(Integer orderId) {
		return orderRepo.findById(orderId).map(ordermapper::fromOrder)
				.orElseThrow(() -> new EntityNotFoundException("No order found with Id: "+orderId));
	}

}
