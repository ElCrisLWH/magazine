package com.falabella.magazine.product;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	public Product getProduct(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException(
			"product wiht id " + productId + "does not exist"));
		return product;
	}

	public void createProduct(Product product) {
		if(product.getSKU() == null){
			throw new IllegalArgumentException("product sku cannot be empty");
		}
		Pattern pattern = Pattern.compile("[A-Z]{3}-[1-9][0-9]{8}");
		if(pattern.matcher(product.getSKU()).matches()){
			throw new IllegalArgumentException("product sku format is invalid");
		}
		Optional<Product> productOptional = productRepository.findProductbySKU(product.getSKU());
		if (productOptional.isPresent()) {
			throw new IllegalArgumentException("product sku already in use");
		}

		if(product.getName() == null){
			throw new IllegalArgumentException("product name cannot be empty");
		}
		if(product.getName().length() > 50 || product.getName().length() < 3){
			throw new IllegalArgumentException("product name must be at least 3 characters and at most 50 characters long");
		}

		if(product.getBrand() == null){
			throw new IllegalArgumentException("product brand cannot be empty");
		}
		if(product.getBrand().length() > 50 || product.getBrand().length() < 3){
			throw new IllegalArgumentException("product brand length must be between 3 and 50 characters long");
		}

		if(product.getSize() != null && product.getSize().isEmpty() ){
			throw new IllegalArgumentException("product size cannot be blank");
		}

		if(product.getPrice() == null){
			throw new IllegalArgumentException("product price cannot be empty");
		}
		if(product.getPrice() > 99999999 || product.getPrice() < 1){
			throw new IllegalArgumentException("product price must be between 1 and 99999999");
		}

		if(product.getPrincipalImageURL() == null){
			throw new IllegalArgumentException("product principal image url cannot be empty");
		}

		productRepository.save(product);
	}

	public void deleteProduct(Long productId) {
		boolean exists = productRepository.existsById(productId);
		if (!exists) {
			throw new IllegalArgumentException("product with id " + productId + " does not exist");
		}
		productRepository.deleteById(productId);
	}

	@Transactional
	public void updateProduct(Long productId, String sku, String name, String brand, String size, Float price, String principalImageURL, List<String> otherImageURLs) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException(
			"product wiht id " + productId + "does not exist"));

		if(name != null && product.getName() != name) {
			if(product.getName().length() > 50 || product.getName().length() < 3){
				throw new IllegalArgumentException("product name must be at least 3 characters and at most 50 characters long");
			}
			product.setName(name);
		}
		if(brand != null && product.getBrand() != brand) {
			if(product.getBrand().length() > 50 || product.getBrand().length() < 3){
				throw new IllegalArgumentException("product brand length must be between 3 and 50 characters long");
			}
			product.setBrand(brand);
		}

		if(size != null && product.getSize() != size) {
			if(product.getSize() != null && product.getSize().isEmpty() ){
				throw new IllegalArgumentException("product size cannot be blank");
			}
			product.setName(name);
		}

		if(price != null && product.getPrice() != price){
			if(product.getPrice() > 99999999 || product.getPrice() < 1){
				throw new IllegalArgumentException("product price must be between 1 and 99999999");
			}
			product.setPrice(price);
		}
		
		if(principalImageURL != null && product.getPrincipalImageURL() != principalImageURL){
			product.setPrincipalImageURL(principalImageURL);
		}
	}
}