package com.example.nobsv2.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull(message = "Name is required")
	@Column(name = "name")
	private String name;
	
	@Size(min = 20, message = "Description must be 20 characters long")
	@Column(name = "description")
	private String description;
	
	@PositiveOrZero(message = "Price must not be negative")
	@Column(name = "price")
	private Double price;
	
	
}
