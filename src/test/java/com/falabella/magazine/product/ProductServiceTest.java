package com.falabella.magazine.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	private Product product1, product2;

	@BeforeEach
	public void setUp() {
		System.out.println(productRepository);
		product1 = new Product(
			"FAL-840627000",
			"500 Zapatilla Urbana Mujer",
			"New Balance",
			"37",
			42990.0f,
			"https://falabella.scene7.com/is/image/Falabella/8406270_1"
		);
		product2 = new Product(
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
	void testCreateProductOk() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		productService.createProduct(product1);
		Mockito.verify(productRepository).save(product1);
	}

	@Test
	void testCreateProductOkNoSize() {
		product1.setSize(null);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		productService.createProduct(product1);
		Mockito.verify(productRepository).save(product1);
	}

	@Test
	void testCreateProductOkNoOtherImageURLs() {
		product1.setOtherImageURLs(null);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		productService.createProduct(product1);
		Mockito.verify(productRepository).save(product1);
	}

	@Test
	void testCreateProductNoSKU() {
		product1.setSKU(null);
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductInvalidSKUFormat() {
		product1.setSKU("hello");
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductSKUAlreadyExists() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.CONFLICT, error.getStatusCode());
	}

	@Test
	void testCreateProductNoName() {
		product1.setName(null);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductNameTooLong() {
		product1.setName("A extremly long name that will not fit under the 50 character maximum limit");
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}
	
	@Test
	void testCreateProductNameTooShort() {
		product1.setName("Hi");
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductNoBrand() {
		product1.setBrand(null);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductBrandTooLong() {
		product1.setBrand("A extremly long brand that will not fit under the 50 character maximum limit");
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductBrandTooShort() {
		product1.setBrand("Hi");
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductBlankSize() {
		product1.setSize("");
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductNoPrice() {
		product1.setPrice(null);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductPriceTooExpensive() {
		product1.setPrice(10000000000.0f);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductPriceTooCheap() {
		product1.setPrice(0.5f);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductNoPrincipalImageURL() {
		product1.setPrincipalImageURL(null);
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductInvalidPrincipalImageURLFormat() {
		product1.setPrincipalImageURL("this is not an url");
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testCreateProductInvalidOtherImageURLsFormat() {
		product1.setOtherImageURLs(List.of("https://falabella.scene7.com/is/image/Falabella/8406270_2", "this is not an url"));
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
		() -> {
			productService.createProduct(product1);
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}
	
	@Test
	void testDeleteProductOk() {
		Mockito.when(productRepository.existsById("FAL-881952283")).thenReturn(true);
		productService.deleteProduct("FAL-881952283");
		Mockito.verify(productRepository).deleteById("FAL-881952283");
	}

	@Test
	void testDeleteProductNotFound() {
		Mockito.when(productRepository.existsById("FAL-999999999")).thenReturn(false);
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.deleteProduct("FAL-999999999");
		});
		assertEquals(HttpStatus.NOT_FOUND, error.getStatusCode());
	}

	@Test
	void testGetProductOk() {
		Mockito.when(productRepository.findById("FAL-881952283")).thenReturn(Optional.of(product2));
		assertEquals(product2, productService.getProduct("FAL-881952283"));
	}

	@Test
	void testGetProductNotFound() {
		Mockito.when(productRepository.findById("FAL-999999999")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
				productService.getProduct("FAL-999999999");
		});
		assertEquals(HttpStatus.NOT_FOUND, error.getStatusCode());
	}

	@Test
	void testListProducts() {
		List<Product> products = List.of(product1, product2);
		Mockito.when(productRepository.findAll()).thenReturn(products);
		assertEquals(products, productService.listProducts());
	}

	@Test
	void testUpdateProductOk() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product( 
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	
	@Test
	void testUpdateProductOkNoName() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product(
			"FAL-840627000",
			null,
			"New Brand",
			"L",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"500 Zapatilla Urbana Mujer",
			"New Brand",
			"L",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	@Test
	void testUpdateProductOkNoBrand() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product(
			"FAL-840627000",
			"New Shoe Name",
			null,
			"L",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Balance",
			"L",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	@Test
	void testUpdateProductOkNoSize() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			null,
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"37",
			15000.0F,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	@Test
	void testUpdateProductOkNoPrice() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			null,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			42990.0f,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	@Test
	void testUpdateProductOkNoPrincipalImageURL() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			15000.0f,
			null,
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			15000.0f,
			"https://falabella.scene7.com/is/image/Falabella/8406270_1",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	@Test
	void testUpdateProductOkNoOptional() {
		product1.setOtherImageURLs(List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4"));
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		productService.updateProduct( new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			15000.0f,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			null
		));
		Product updatedProduct = new Product(
			"FAL-840627000",
			"New Shoe Name",
			"New Brand",
			"L",
			15000.0f,
			"https://falabella.scene7.com/is/image/Falabella/8406270_2",
			List.of("https://falabella.scene7.com/is/image/Falabella/8406270_3","https://falabella.scene7.com/is/image/Falabella/8406270_4")
		);
		Mockito.verify(productRepository).save(updatedProduct);
	}

	@Test
	void testUpdateProductNoSKU() {
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				null,
				"New Shoe Name",
				null,
				null,
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductNotFound() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.empty());
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				"New Shoe Name",
				null,
				null,
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.NOT_FOUND, error.getStatusCode());
	}

	@Test
	void testUpdateProductNameTooLong() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				"A extremly long name that will not fit under the 50 character maximum limit",
				null,
				null,
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}
	
	@Test
	void testUpdateProductNameTooShort() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				"Hi",
				null,
				null,
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductBrandTooLong() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				null,
				"A extremly long brand that will not fit under the 50 character maximum limit",
				null,
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}
	
	@Test
	void testUpdateProductBrandTooShort() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				null,
				"Hi",
				null,
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductBlankSize() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				"New Shoe Name",
				null,
				"",
				null,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductPriceTooExpensive() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				"New Shoe Name",
				null,
				null,
				10000000000.0f,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductPriceTooCheap() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				"New Shoe Name",
				null,
				null,
				0.5f,
				null,
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductInvalidPrincipalImageURLFormat() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				null,
				null,
				null,
				0.5f,
				"this is not an url",
				null
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}

	@Test
	void testUpdateProductInvalidOtherImageURLsFormat() {
		Mockito.when(productRepository.findById("FAL-840627000")).thenReturn(Optional.of(product1));
		HttpClientErrorException error = assertThrows(HttpClientErrorException.class, 
			() -> {
			productService.updateProduct( new Product(
				"FAL-840627000",
				null,
				null,
				null,
				null,
				null,
				List.of("https://falabella.scene7.com/is/image/Falabella/8406270_2", "this is not an url")
			));
		});
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
	}
}
