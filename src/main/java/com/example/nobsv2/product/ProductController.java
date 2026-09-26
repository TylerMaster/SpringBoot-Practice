package com.example.nobsv2.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.model.ErrorResponse;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.CreateProductService;
import com.example.nobsv2.product.services.DeleteProductService;
import com.example.nobsv2.product.services.GetProductService;
import com.example.nobsv2.product.services.GetProductsService;
import com.example.nobsv2.product.services.SearchProductService;
import com.example.nobsv2.product.services.UpdateProductService;

@RestController
public class ProductController {
	
	
	private final CreateProductService createProductService;
	
	
	private final GetProductsService getProductsService;
	
	
	private final UpdateProductService updateProductService;
	
	
	private final DeleteProductService deleteProductService;
	
	private final GetProductService getProductService;
	
	private final SearchProductService searchProductService;
	
	public ProductController(CreateProductService createProductService,
							 GetProductsService getProductsService,
							 UpdateProductService updateProductService,
							 DeleteProductService deleteProductService,
							 GetProductService getProductService,
							 SearchProductService searchProductService) {
		
		this.createProductService = createProductService;
		this.getProductsService = getProductsService;
		this.updateProductService = updateProductService;
		this.deleteProductService = deleteProductService;
		this.getProductService = getProductService;
		this.searchProductService = searchProductService;
	}

	


@PostMapping("/product")
public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
	
			return createProductService.execute(product);
	
}

@GetMapping("/products")
public ResponseEntity<List<ProductDTO>> getProducts() {
	return getProductsService.execute(null);
}

@GetMapping("/product/{id}")
public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Integer id){
	return getProductService.execute(id);
	
}
@GetMapping("/product/search")
public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam("name") String name){
	return searchProductService.execute(name);
	
}

@PutMapping("/product/{id}") 
public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
	return updateProductService.execute(new UpdateProductCommand(id, product));

}


@DeleteMapping("/product/{id}")
public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
	return deleteProductService.execute(id);
}



/*@ExceptionHandler(ProductNotFoundException.class)
public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundExceptions exception){
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
}*/
}
