package com.microservices.product.payload;

import java.math.BigDecimal;

import com.microservices.product.entities.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductResquest(
		Integer id,
		@NotNull(message = "Product name is requied.")
		String name,
		String description,
		@Positive(message = "Available quantity should be positive.")
		double availableQty,
		@Positive(message = "Price should be positive.")
		BigDecimal price,
		@NotNull(message = "Product category is required")
		Category category
) {
}
