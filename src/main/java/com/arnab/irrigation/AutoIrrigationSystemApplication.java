package com.arnab.irrigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoIrrigationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoIrrigationSystemApplication.class, args);
	}

}
