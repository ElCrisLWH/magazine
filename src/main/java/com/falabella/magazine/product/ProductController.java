package com.falabella.magazine.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> listProducts() {
		return productService.listProducts();
	}

	@GetMapping(path = "{sku}")
	public Product getProduct(@PathVariable("sku") String sku) {
		return productService.getProduct(sku);
	}

	@PostMapping
	public void createProduct(Product product) {
		productService.createProduct(product);
	}

	@DeleteMapping(path = "{sku}")
	public void deleteProduct(@PathVariable("sku") String sku) {
		productService.deleteProduct(sku);
	}

	@PutMapping(path = "{sku}")
	public void updateProduct(
		@PathVariable("sku") String sku,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String brand,
		@RequestParam(required = false) String size,
		@RequestParam(required = false) Float price,
		@RequestParam(required = false) String principalImageURL,
		@RequestParam(required = false) List<String> otherImageURLs) {
			productService.updateProduct(sku, name, brand, size, price, principalImageURL, otherImageURLs);
	}
}
