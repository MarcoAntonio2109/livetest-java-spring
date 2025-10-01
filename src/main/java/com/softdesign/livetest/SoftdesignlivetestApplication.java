package com.softdesign.livetest;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableMongock
@SpringBootApplication
public class SoftdesignlivetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftdesignlivetestApplication.class, args);
	}

}
