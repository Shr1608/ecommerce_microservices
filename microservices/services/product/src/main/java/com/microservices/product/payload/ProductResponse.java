package com.microservices.product.payload;

import java.math.BigDecimal;

public record ProductResponse(
		Integer id,
		String name,
		String description,
		double availableQty,
		BigDecimal price,
		Integer category_id,
		String category_name,
		String category_description
){}
