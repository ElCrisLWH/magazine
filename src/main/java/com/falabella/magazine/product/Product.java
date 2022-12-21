package com.falabella.magazine.product;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	@Id
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
			"sku: " + sku +
			"name: " + name +
			"brand: " + brand +
			"size: " + size +
			"price: " + price +
			"principalImageURL: " + principalImageURL +
			"otherImageURLs: " + otherImageURLs.toString() +
			"}";
	}
	
	public Boolean hasValidPrincipalImageURL() {
		return isValidURL(principalImageURL);	
	}
	
	public Boolean hasValidOtherImageURLs() {
		if(otherImageURLs != null){
			for (int i = 0; i < otherImageURLs.size(); i++) {
				if(!isValidURL(otherImageURLs.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public static Boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}
}
