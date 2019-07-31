package com.weddingorganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.weddingorganizer.security.MyUserDetailsService;

@SpringBootApplication
@ComponentScan(basePackageClasses = MyUserDetailsService.class)
public class WeddingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingAppApplication.class, args);
	}

}
