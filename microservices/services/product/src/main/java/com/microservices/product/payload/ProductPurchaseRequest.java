package com.microservices.product.payload;

public record ProductPurchaseRequest(
		Integer productId,
		Integer quantity
) {
}
