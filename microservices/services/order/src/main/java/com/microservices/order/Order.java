package com.microservices.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.microservices.order.utils.OrderLine;
import com.microservices.order.utils.PaymentMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "customer_order")
public class Order {
	  @Id
	  @GeneratedValue
	  private Integer id;

	  @Column(unique = true,  nullable = false)
	  private String reference;

	  private BigDecimal totalAmount;

	  @Enumerated(EnumType.STRING)
	  private PaymentMethod paymentMethod;

	  private String customerId;

	  @OneToMany(mappedBy = "order")
	  private List<OrderLine> orderLines;

	  @CreatedDate
	  @Column(updatable = false, nullable = false)
	  private LocalDateTime createdDate;

	  @LastModifiedDate
	  @Column(insertable = false)
	  private LocalDateTime lastModifiedDate;
}
