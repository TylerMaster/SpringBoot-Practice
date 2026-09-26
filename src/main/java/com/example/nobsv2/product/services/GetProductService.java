package com.example.nobsv2.product.services;



import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Query;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.model.ProductDTO;

@Service
public class GetProductService implements Query<Integer, ProductDTO> {

	private final ProductRepository productRepository;
	
	public GetProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
		
	}
	
	
	@Override
	public ResponseEntity<ProductDTO> execute(Integer input) {
		Optional<Product> productOptional = productRepository.findById(input);
		if(productOptional.isPresent()) {
			return ResponseEntity.ok(new ProductDTO(productOptional.get()));
		}
		throw new ProductNotFoundException();
	}

}
