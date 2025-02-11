package com.library.libraryBook;

import com.library.libraryBook.controller.dto.BookBuildInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;






@OpenAPIDefinition(
		info = @Info(
				title = "Library books microservice REST API Documentation",
				description = "Library books microservice REST API Documentation",
				version = "v1"
		)
)
@SpringBootApplication
@EnableConfigurationProperties(value = {BookBuildInfoDto.class})
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
