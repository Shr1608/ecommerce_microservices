package com.microservices.order.payload;

import java.math.BigDecimal;

public record PurchaseResponse (
		Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
		){
}
