package org.ecommerceChallenge.gateway;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class GatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/product-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://product-service"))
				.route(r -> r.path("/stock-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://stock-service"))
				.route(r -> r.path("/order-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://order-service"))
				.route(r -> r.path("/customer-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://customer-service"))
				.build();
	}

}
