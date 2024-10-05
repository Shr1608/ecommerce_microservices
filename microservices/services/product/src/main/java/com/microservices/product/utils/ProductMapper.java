package com.microservices.product.utils;

import org.springframework.stereotype.Service;

import com.microservices.product.entities.Category;
import com.microservices.product.entities.Product;
import com.microservices.product.payload.ProductPurchaseResponse;
import com.microservices.product.payload.ProductResponse;
import com.microservices.product.payload.ProductResquest;

@Service
public class ProductMapper {

	public Product toProduct(ProductResquest req) {
		return Product.builder()
				.id(req.id())
				.name(req.name())
				.description(req.description())
				.availableQty(req.availableQty())
				.price(req.price())
				.category(Category.builder()
						.id(req.category().getId()).build())
				.build();
	}
	
	public ProductResponse toProductResponse(Product product) {
		return new ProductResponse(product.getId(),
				product.getName(), product.getDescription(), product.getAvailableQty(), product.getPrice(),
				product.getCategory().getId(), product.getCategory().getName(),
				product.getCategory().getDescription());
	}

	public ProductPurchaseResponse toproductPurchaseResponse(Product product, Integer quantity) {
		return new ProductPurchaseResponse(product.getId(), product.getName(),
				product.getDescription(), product.getPrice(), quantity);
	}
}
