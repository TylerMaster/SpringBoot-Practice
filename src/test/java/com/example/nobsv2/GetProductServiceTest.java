package com.example.nobsv2;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductService;

public class GetProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private GetProductService getProductService;
	
	@BeforeEach
	public void setup() {
		
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void given_product_exists_when_get_product_service_return_product_dto() {
		//given
		Product product = new Product();
		product.setId(1);
		product.setName("Product Name");
		product.setDescription("Product Description");
		product.setPrice(9.99);
		
		//this says 'when' but its actually still setting up
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		
		//when
		ResponseEntity<ProductDTO> response = getProductService.execute(1);
		
		
		//then
		assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
		verify(productRepository, times(1)).findById(1);
	}
	
	@Test
	public void given_product_does_not_exist_when_get_product_service_throw_product_not_found_exception() {
	//given
		when(productRepository.findById(1)).thenReturn(Optional.empty());
	
	
	//when & then
	assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
	verify(productRepository, times(1)).findById(1);
	
	
	}	
}
