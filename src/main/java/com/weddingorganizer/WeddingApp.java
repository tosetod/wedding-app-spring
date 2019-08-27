package com.weddingorganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackageClasses = {MyUserDetailsService.class, IUserService.class})
public class WeddingApp {

	public static void main(String[] args) {
		SpringApplication.run(WeddingApp.class, args);
	}

}
