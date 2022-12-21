package com.falabella.magazine.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
			Product zapatilla = new Product(
				"FAL-840627000",
				"500 Zapatilla Urbana Mujer",
				"New Balance",
				"37",
				42990.0f,
				"https://falabella.scene7.com/is/image/Falabella/8406270_1"
			);
			Product bicicleta = new Product(
				"FAL-881952283",
				"Bicicleta Baltoro Aro 29",
				"Jeep",
				"ST",
				399990.0f,
				"https://falabella.scene7.com/is/image/Falabella/881952283_1",
				List.of("https://falabella.scene7.com/is/image/Falabella/881952283_2")
			);
			Product camisa = new Product(
				"FAL-881898502",
				"Camisa Manga Corta Hombre",
				"Basement",
				"M",
				24990.0f,
				"https://falabella.scene7.com/is/image/Falabella/881898502_1"
			);
			productRepository.saveAll(List.of(zapatilla, bicicleta, camisa));
		};
	}
}
