package com.falabella.magazine.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	// The internal stock-keeping unit is used as product id
	@Id
	@SequenceGenerator(
		name = "product_sequence",
		sequenceName = "product_sequence",
		allocationSize = 1
		
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "product_sequence"
	)
	private Long id;
	private String sku;
	private String name;
	private String brand;
	private String size;
	private Float price;
	private String principalImageURL;
	@Column
    @ElementCollection(targetClass=String.class)
	private List<String> otherImageURLs;

	public Product() {
	}

	public Product(Long id, String sku, String name, String brand, String size, Float price, String principalImageURL) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.principalImageURL = principalImageURL;
	}

	public Product(String sku, String name, String brand, String size, Float price, String principalImageURL, List<String> otherImageURLs) {
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.principalImageURL = principalImageURL;
		this.otherImageURLs = otherImageURLs;
	}

	public Product(String sku, String name, String brand, String size, Float price, String principalImageURL) {
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.principalImageURL = principalImageURL;
	}

	public Product(String sku, String name, String brand, Float price, String principalImageURL, List<String> otherImageURLs) {
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.principalImageURL = principalImageURL;
		this.otherImageURLs = otherImageURLs;
	}

	public Product(String sku, String name, String brand, Float price, String principalImageURL) {
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.principalImageURL = principalImageURL;
	}

	public Long getId() {
		return this.id;
	}

	public String getSKU() {
		return this.sku;
	}

	public String getName() {
		return this.name;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getSize() {
		return this.size;
	}

	public Float getPrice() {
		return this.price;
	}

	public String getPrincipalImageURL() {
		return this.principalImageURL;
	}

	public List<String> getOtherImageURLs() {
		return this.otherImageURLs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSKU(String sku) {
		this.sku = sku;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setPrincipalImageURL( String  principalImageURL) {
		this.principalImageURL = principalImageURL;
	}

	public void setOtherImageURLs(List<String> otherImageURLs) {
		this.otherImageURLs = otherImageURLs;
	}

	@Override
	public String toString() {
		return "Product{"+
			"id: " + id +
			"name: " + name +
			"brand: " + brand +
			"size: " + size +
			"price: " + price +
			"principalImageURL: " + principalImageURL +
			"otherImageURLs: " + otherImageURLs.toString() +
			"}";
	}
}
