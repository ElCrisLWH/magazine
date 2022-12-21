package com.falabella.magazine.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductTest {
	private Product product;

	@BeforeEach
	public void setUp() {
		product = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
	}

	@Test
	void testGetBrand() {
		assertEquals("Jeep", product.getBrand());
	}

	@Test
	void testGetName() {
		assertEquals("Bicicleta Baltoro Aro 29", product.getName());
	}

	@Test
	void testGetOtherImageURLs() {
		assertEquals(List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2"), product.getOtherImageURLs());
	}

	@Test
	void testGetPrice() {
		assertEquals(399990.0f, product.getPrice());
	}

	@Test
	void testGetPrincipalImageURL() {
		assertEquals("https://falabella.scene7.com/is/image/Falabella/881952283_1", product.getPrincipalImageURL());
	}

	@Test
	void testGetSKU() {
		assertEquals("FAL-881952283",product.getSKU());
	}

	@Test
	void testGetSize() {
		assertEquals("ST",product.getSize());
	}

	@Test
	void testHasValidOtherImageURLs() {
		assertTrue(product.hasValidOtherImageURLs());
	}

	@Test
	void testHasValidPrincipalImageURL() {
		assertTrue(product.hasValidPrincipalImageURL());
	}

	@Test
	void testIsValidURLTrue() {
		assertTrue(Product.isValidURL("https://falabella.scene7.com/is/image/Falabella/8406270_1"));
	}

	@Test
	void testIsValidURLFalse() {
		assertFalse(Product.isValidURL("https://falabella.scene7.com/ this will fail"));
	}

	@Test
	void testSetBrand() {
		product.setBrand("Mountain X");
		assertEquals("Mountain X", product.getBrand());
	}

	@Test
	void testSetName() {
		product.setName("Bicicleta Aro 32");
		assertEquals("Bicicleta Aro 32", product.getName());
	}

	@Test
	void testSetOtherImageURLs() {
		List<String> imageURLs = List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2","https://falabella.scene7.com/is/image/Falabella/881952283_3");
		product.setOtherImageURLs(imageURLs);
		assertEquals(imageURLs, product.getOtherImageURLs());
	}

	@Test
	void testSetPrice() {
		product.setPrice(45000.0f);
		assertEquals(45000.0f, product.getPrice());
	}

	@Test
	void testSetPrincipalImageURL() {
		String imageURL = "https://falabella.scene7.com/is/image/Falabella/881952283_3";
		product.setPrincipalImageURL(imageURL);
		assertEquals(imageURL, product.getPrincipalImageURL());
	}

	@Test
	void testSetSKU() {
		product.setSKU("FAL-100000000");
		assertEquals("FAL-100000000", product.getSKU());
	}

	@Test
	void testSetSize() {
		product.setSize("L");
		assertEquals("L", product.getSize());
	}

	@Test
	void testToString() {
		assertEquals("Product{"+
		"sku: FAL-881952283"+
		",name: Bicicleta Baltoro Aro 29"+
		",brand: Jeep" +
		",size: ST" +
		",price: 399990.0" +
		",principalImageURL: https://falabella.scene7.com/is/image/Falabella/881952283_1"+
		",otherImageURLs: [https://falabella.scene7.com/is/image/Falabella/881952283_2]" +
		"}",product.toString());
	}

	@Test
	void testEqualsTrueSameInstance() {
		assertTrue(product.equals(product));
	}

	@Test
	void testEqualsTrueDifferentInstance() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertTrue(product.equals(anotherProduct));
	}

	@Test
	void testEqualsFalseDifferentClass() {
		assertFalse(product.equals("this is not a product"));
	}
	
	@Test
	void testEqualsFalseDifferentSKU() {
		Product anotherProduct = new Product(
			"FAL-881952284",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertFalse(product.equals(anotherProduct));
	}
	
	@Test
	void testEqualsFalseDifferentName() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 30",
			"Jeep",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertFalse(product.equals(anotherProduct));
	}
	
	@Test
	void testEqualsFalseDifferentBrand() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Car",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertFalse(product.equals(anotherProduct));
	}
	
	@Test
	void testEqualsFalseDifferentSize() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"L",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertFalse(product.equals(anotherProduct));
	}
	
	@Test
	void testEqualsFalseDifferentPrice() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"ST",
			15000.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertFalse(product.equals(anotherProduct));
	}
	
	@Test
	void testEqualsFalseDifferentPrincipalImageURL() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_3",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
		);
		assertFalse(product.equals(anotherProduct));
	}
	
	@Test
	void testEqualsFalseDifferentOtherImageURLs() {
		Product anotherProduct = new Product(
			"FAL-881952283",
			"Bicicleta Baltoro Aro 29",
			"Jeep",
			"ST",
			399990.0f,
			"https://falabella.scene7.com/is/image/Falabella/881952283_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/881952283_3")
		);
		assertFalse(product.equals(anotherProduct));
	}
}
