package com.microservices.notification.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservices.notification.email.EmailService;
import com.microservices.notification.notification.Notification;
import com.microservices.notification.notification.NotificationRepository;
import com.microservices.notification.notification.NotificationType;
import com.microservices.notification.order.OrderConfirmation;
import com.microservices.notification.payment.PaymentConfirmation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
	
	private final NotificationRepository notificationRepository;
	private final EmailService emailService;

	@KafkaListener(topics = "payment-topic")
	public void paymentSuccessNotification(PaymentConfirmation confirmation) throws MessagingException{
		System.err.println("Consuming the payment message from payment-topic."+confirmation);
		System.err.println(notificationRepository.save(Notification.builder()
		.notificationType(NotificationType.PAYMENT_CONFIRMATION)
		.notificationDate(LocalDateTime.now())
		.paymentConfirmation(confirmation)
		.build()));
		
		//send email
		emailService.sendPaymentSuccessEmail(confirmation.customerEmail(),
				confirmation.customerFirstname()+confirmation.customerLastname(),
				confirmation.amount(), confirmation.orderRefernce());
		
	}
	
	
	@KafkaListener(topics = "order-topic")
	public void orderSuccessNotification(OrderConfirmation confirmation) throws MessagingException{
		log.info("Consuming the order message from payment-topic."+confirmation);
		System.err.println(notificationRepository.save(Notification.builder()
		.notificationType(NotificationType.ORDER_CONFIRMATION)
		.notificationDate(LocalDateTime.now())
		.orderConfirmation(confirmation)
		.build()));
		
		//send email
		emailService.sendOrderConfirmationEmail(confirmation.customer().email(),
						confirmation.customer().firstname()+confirmation.customer().lastname(),
						confirmation.totalAmount(), confirmation.orderRefernce(),
						confirmation.products());
	}
}
