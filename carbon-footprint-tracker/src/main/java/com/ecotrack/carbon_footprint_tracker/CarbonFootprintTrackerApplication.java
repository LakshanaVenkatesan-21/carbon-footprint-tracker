package com.ecotrack.carbon_footprint_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ecotrack")
@EnableJpaRepositories(basePackages = "com.ecotrack.repository")
@EntityScan(basePackages = "com.ecotrack.model")
public class CarbonFootprintTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarbonFootprintTrackerApplication.class, args);
	}

}
