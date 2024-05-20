package com.bisbis.bisbis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@EntityScan
@ComponentScan
public class BisbisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BisbisApplication.class, args);
	}

}
