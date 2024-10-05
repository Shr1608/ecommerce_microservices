package com.microservices.product.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="category")
@Builder
public class Category {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<Product> products;

}
