package com.roy.explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EnterpriseAppLookupApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseAppLookupApplication.class, args);
	}

}
