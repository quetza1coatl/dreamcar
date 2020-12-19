package com.dreamcar.auctionplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuctionPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionPlatformApplication.class, args);
	}

}
