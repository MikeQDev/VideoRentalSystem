package com.vrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vrs.database.WebSqlExecutor;

@SpringBootApplication
public class VideoRentalSystemApplication {
	
	/**
	 * Entry point
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(VideoRentalSystemApplication.class, args);
		
	}
}
