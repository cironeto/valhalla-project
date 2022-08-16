package dev.cironeto.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("dev.cironeto")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("access-control-service_route",
                        route -> route
                                .path("/gateway/access-control-service/**")
                                .filters(f -> f.rewritePath("/gateway/access-control-service/(?<RID>.*)", "/access-control-service/${RID}"))
                                .uri("http://localhost:8090"))
                .route("keycloak_route",
                        route -> route
                                .path("/gateway/keycloak/**")
                                .filters(f -> f.rewritePath("/gateway/keycloak/(?<RID>.*)", "/keycloak/${RID}"))
                                .uri("http://localhost:8090"))
                .build();
    }

}
