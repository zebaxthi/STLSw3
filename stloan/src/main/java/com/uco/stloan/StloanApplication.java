package com.uco.stloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.uco.stloan")
public class StloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(StloanApplication.class, args);
	}

}
