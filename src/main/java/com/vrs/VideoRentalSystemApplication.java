package com.vrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vrs.database.SQLExecutor;

@SpringBootApplication
public class VideoRentalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalSystemApplication.class, args);
		
	}
}
