package com.msci.test.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.msci.test.model")
@EnableJpaRepositories(basePackages = "com.msci.test.repository")
@SpringBootApplication(scanBasePackages = "com.msci.test")
public class MsciTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsciTestApplication.class, args);
	}

}
