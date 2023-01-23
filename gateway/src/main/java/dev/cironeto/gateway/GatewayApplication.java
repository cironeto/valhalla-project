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
                .route("business_function_route",
                        route -> route
                                .path("/gateway/business-function/**")
                                .filters(f -> f.rewritePath("/gateway/business-function/(?<RID>.*)", "/business-function/${RID}"))
                                .uri("http://localhost:8090"))
                .route("permission_route",
                        route -> route
                                .path("/gateway/permission/**")
                                .filters(f -> f.rewritePath("/gateway/permission/(?<RID>.*)", "/permission/${RID}"))
                                .uri("http://localhost:8090"))
                .route("business-function-permission_route",
                        route -> route
                                .path("/gateway/business-function-permission/**")
                                .filters(f -> f.rewritePath("/gateway/business-function-permission/(?<RID>.*)", "/business-function-permission/${RID}"))
                                .uri("http://localhost:8090"))
                .route("user_route",
                        route -> route
                                .path("/gateway/user/**")
                                .filters(f -> f.rewritePath("/gateway/user/(?<RID>.*)", "/user/${RID}"))
                                .uri("http://localhost:8090"))
                .route("profile_route",
                        route -> route
                                .path("/gateway/profile/**")
                                .filters(f -> f.rewritePath("/gateway/profile/(?<RID>.*)", "/profile/${RID}"))
                                .uri("http://localhost:8090"))
                .route("user-profile_route",
                        route -> route
                                .path("/gateway/user-profile/**")
                                .filters(f -> f.rewritePath("/gateway/user-profile/(?<RID>.*)", "/user-profile/${RID}"))
                                .uri("http://localhost:8090"))
                .route("user-profile_route",
                        route -> route
                                .path("/gateway/profile-business-function-permission/**")
                                .filters(f -> f.rewritePath("/gateway/profile-business-function-permission/(?<RID>.*)", "/profile-business-function-permission/${RID}"))
                                .uri("http://localhost:8090"))
                .build();
    }

}
