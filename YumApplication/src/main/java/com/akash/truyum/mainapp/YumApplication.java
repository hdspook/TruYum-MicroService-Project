package com.akash.truyum.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients("com")
@ComponentScan({"com"})
public class YumApplication {

	public static void main(String[] args) {
		SpringApplication.run(YumApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplateBuilder().build();
	}

}
