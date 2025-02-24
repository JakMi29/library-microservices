package com.library.rental;

import com.library.rental.controller.dto.RentalsBuildInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@OpenAPIDefinition(
		info = @Info(
				title = "Rentals microservice REST API Documentation",
				description = "Rentals microservice REST API Documentation",
				version = "v1"
		)
)
@SpringBootApplication
@EnableConfigurationProperties(value = {RentalsBuildInfoDto.class})
public class RentalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalsApplication.class, args);
	}

}
