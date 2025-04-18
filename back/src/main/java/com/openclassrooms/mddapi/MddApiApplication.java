package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")
public class MddApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MddApiApplication.class, args);
	}

}
