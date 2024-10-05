package com.microservices.product.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservices.product.entities.Product;
import com.microservices.product.exception.ProductPurchaseException;
import com.microservices.product.payload.ProductPurchaseRequest;
import com.microservices.product.payload.ProductPurchaseResponse;
import com.microservices.product.payload.ProductResponse;
import com.microservices.product.payload.ProductResquest;
import com.microservices.product.repository.ProductRepository;
import com.microservices.product.utils.ProductMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	public final ProductRepository productRepo;
	
	public final ProductMapper productMapper;

	public Integer createProduct(@Valid ProductResquest request) {
		Product product =  productMapper.toProduct(request);
		return productRepo.save(product).getId();
	}

	public List<ProductPurchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> request) {
		var productIds = request.stream().map(ProductPurchaseRequest::productId).collect(Collectors.toList());
		
		var storedProducts = productRepo.findAllByIdInOrderById(productIds);
		
		if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
		 var sortedRequest = request
	                .stream()
	                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
	                .toList();
		
	     var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
	        for (int i = 0; i < storedProducts.size(); i++) {
	            var product = storedProducts.get(i);
	            var productRequest = sortedRequest.get(i);
	            if (product.getAvailableQty() < productRequest.quantity()) {
	                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
	            }
	            var newAvailableQuantity = product.getAvailableQty() - productRequest.quantity();
	            product.setAvailableQty(newAvailableQuantity);
	            productRepo.save(product);
	            purchasedProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.quantity()));
	        }
	        return purchasedProducts;
	}

	public ProductResponse findProductById(Integer productId) {
		return productRepo.findById(productId)
				.map(productMapper::toProductResponse)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:"+productId));
	}

	public List<ProductResponse> findProduct() {
		return productRepo.findAll()
				.stream()
				.map(productMapper::toProductResponse)
				.collect(Collectors.toList());
	}

}
