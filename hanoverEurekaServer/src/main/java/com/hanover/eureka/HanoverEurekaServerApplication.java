package com.hanover.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HanoverEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanoverEurekaServerApplication.class, args);
	}
}
