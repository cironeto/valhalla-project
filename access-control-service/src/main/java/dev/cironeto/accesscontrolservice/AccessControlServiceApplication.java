package dev.cironeto.accesscontrolservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
//@EnableJpaRepositories({"dev.cironeto.accesscontrolservice.dummy_endpoint.repository"})
public class AccessControlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessControlServiceApplication.class, args);
    }

}
