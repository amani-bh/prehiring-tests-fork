package com.courzelo_for_business.prehiringtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PrehiringTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrehiringTestsApplication.class, args);
	}

}
