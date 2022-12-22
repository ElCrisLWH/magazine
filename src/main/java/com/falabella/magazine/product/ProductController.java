package com.falabella.magazine.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> listProducts() {
		return ResponseEntity.status(HttpStatus.OK)
		.body(productService.listProducts());
	}

	@GetMapping(path = "{sku}")
	public ResponseEntity<Product> getProduct(@PathVariable("sku") String sku) {
		return ResponseEntity.status(HttpStatus.OK)
		.body(productService.getProduct(sku));
	}

	@PostMapping
	public ResponseEntity<Void> createProduct(Product product) {
		productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping(path = "{sku}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("sku") String sku) {
		productService.deleteProduct(sku);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping(path = "{sku}")
	public ResponseEntity<Void> updateProduct(
		@PathVariable("sku") String sku,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String brand,
		@RequestParam(required = false) String size,
		@RequestParam(required = false) Float price,
		@RequestParam(required = false) String principalImageURL,
		@RequestParam(required = false) List<String> otherImageURLs) {
			productService.updateProduct(new Product(sku, name, brand, size, price, principalImageURL, otherImageURLs));
			return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException error) {
        return ResponseEntity.status(error.getStatusCode())
			.body(error.getMessage());
    }
}
