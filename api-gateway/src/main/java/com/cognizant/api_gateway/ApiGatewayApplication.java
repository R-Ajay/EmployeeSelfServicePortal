package com.cognizant.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder.routes()
				.route(predicateSpec ->
						predicateSpec.path("/employeeservice/**")
						.filters(gatewayFilterSpec ->
								gatewayFilterSpec
										.rewritePath("/employeeservice/(?<segment>.*)","/${segment}")
										.circuitBreaker(config ->
												config
														.setName("employeeCircuitBreaker")
														.setFallbackUri("forward:/contactSupport"))
						                 )
								.uri("lb://EMPLOYEE-SELF-SERVICE"))
				.build();

	}

}
