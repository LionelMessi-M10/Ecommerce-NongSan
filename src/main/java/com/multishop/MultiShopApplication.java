package com.multishop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // Gọi bean dưới
public class MultiShopApplication {

		public static void main(String[] args) {
			SpringApplication.run(MultiShopApplication.class, args);
		}

}
