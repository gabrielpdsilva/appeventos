package com.appeventos.appeventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppeventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppeventosApplication.class, args);
	}
}
