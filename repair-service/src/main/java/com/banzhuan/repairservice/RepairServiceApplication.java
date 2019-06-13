package com.banzhuan.repairservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RepairServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairServiceApplication.class, args);
	}

}
