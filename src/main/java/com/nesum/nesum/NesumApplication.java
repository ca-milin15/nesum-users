package com.nesum.nesum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class NesumApplication {

	public static void main(String[] args) {
		SpringApplication.run(NesumApplication.class, args);
	}

}
