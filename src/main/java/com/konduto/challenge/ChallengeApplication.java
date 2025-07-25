package com.konduto.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeApplication {

	private static final Logger log = LoggerFactory.getLogger(ChallengeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}


}
