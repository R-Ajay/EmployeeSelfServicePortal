package com.cognizant.backgroundverification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BackgroundVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackgroundVerificationApplication.class, args);
	}

}
