package com.library.users;

import com.library.users.controller.dto.UserBuildInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Users microservice REST API Documentation",
                description = "Library Accounts microservice REST API Documentation",
                version = "v1"
        )
)
@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(value = {UserBuildInfoDto.class})
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
