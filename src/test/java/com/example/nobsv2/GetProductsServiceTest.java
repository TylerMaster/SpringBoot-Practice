package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductsService;
import com.example.nobsv2.product.ProductRepository;

public class GetProductsServiceTest {
	
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private GetProductsService getProductsService;
	
	@BeforeEach
	public void setup() {
		
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void given_products_exist_when_get_products_service_return_product_dtos() {

		
		
		
	    // given

	    Product product1 = new Product();
	    product1.setId(1);
	    product1.setName("Product 1");
	    product1.setDescription("Product 1 Description");
	    product1.setPrice(9.99);

	    Product product2 = new Product();
	    product2.setId(2);
	    product2.setName("Product 2");
	    product2.setDescription("Product 2 Description");
	    product2.setPrice(19.99);

	    List<Product> products = List.of(product1, product2);

	    when(productRepository.findAll()).thenReturn(products);


	    // when

	    ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);


	    // then

	    assertEquals(HttpStatus.OK, response.getStatusCode());

	    assertEquals(2, response.getBody().size());

	    assertEquals("Product 1", response.getBody().get(0).getName());
	    assertEquals("Product 2", response.getBody().get(1).getName());

	    verify(productRepository, times(1)).findAll();
	}
}
