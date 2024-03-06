package com.feign.spotify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

	@SpringBootApplication
	@EnableFeignClients //indicando que vamos usar feign cliente nesse projeto
	public class SpotifyApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(SpotifyApplication.class, args);
		}
	
	}
