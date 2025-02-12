package com.library.gatewayderver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/library/books/**")
						.filters( f -> f.rewritePath("/library/books/(?<segment>.*)","/${segment}"))
						.uri("lb://BOOKS"))
				.route(p -> p
						.path("/library/rentals/**")
						.filters( f -> f.rewritePath("/library/rentals/(?<segment>.*)","/${segment}"))
						.uri("lb://RENTALS"))
				.route(p -> p
						.path("/library/users/**")
						.filters( f -> f.rewritePath("/library/users/(?<segment>.*)","/${segment}"))
						.uri("lb://USERS"))
				.build();
	}

}
