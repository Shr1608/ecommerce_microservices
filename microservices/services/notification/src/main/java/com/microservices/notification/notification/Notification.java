package com.microservices.notification.notification;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.microservices.notification.order.OrderConfirmation;
import com.microservices.notification.payment.PaymentConfirmation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Notification {
	
	@Id
	private String id;
	private NotificationType notificationType;
	private LocalDateTime notificationDate;
	private OrderConfirmation orderConfirmation;
	private PaymentConfirmation paymentConfirmation;
	
}
