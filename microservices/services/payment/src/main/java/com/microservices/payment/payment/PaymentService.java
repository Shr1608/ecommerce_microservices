package com.microservices.payment.payment;

import org.springframework.stereotype.Service;

import com.microservices.payment.notification.NotificationProducer;
import com.microservices.payment.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	  private final PaymentRepository repository;
	  private final PaymentMapper mapper;
	  private final NotificationProducer notificationProducer;

	  public Integer createPayment(PaymentRequest request) {
	    var payment = this.repository.save(this.mapper.toPayment(request));
	    
	    //notification service
	    notificationProducer.sendNotification(
	    		new PaymentNotificationRequest(
	    				request.orderReference()
	    				, request.amount(),
	    				request.paymentMethod(),
	    				request.customer().firstname(),
	    				request.customer().lastname(),
	    				request.customer().email())
	    		);
	    
	    return payment.getId();
	  }
}
