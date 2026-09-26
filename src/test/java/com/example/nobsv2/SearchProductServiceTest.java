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
import com.example.nobsv2.product.services.SearchProductService;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;


public class SearchProductServiceTest {

	
	@Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
	public void when_search_called_then_product_dtos_returned() {
    	
    	Product product1 = new Product();
    	product1.setId(1);
    	product1.setName("iPhone");
    	product1.setDescription("Apple smartphone");
    	product1.setPrice(999.99);

    	Product product2 = new Product();
    	product2.setId(2);
    	product2.setName("Samsung Galaxy");
    	product2.setDescription("Android smartphone");
    	product2.setPrice(899.99);
    	
    	List<Product> products = List.of(product1, product2);
    	
    	when(productRepository.findByNameOrDescriptionContaining("phone"))
        .thenReturn(products);
    	

        ResponseEntity<List<ProductDTO>> response =
                searchProductService.execute("phone");

    	
    	  assertEquals(HttpStatus.OK, response.getStatusCode());

    	    assertEquals(2, response.getBody().size());

    	    assertEquals("iPhone", response.getBody().get(0).getName());
    	    assertEquals("Samsung Galaxy", response.getBody().get(1).getName());

    	    verify(productRepository, times(1))
    	            .findByNameOrDescriptionContaining("phone");
    }

}
