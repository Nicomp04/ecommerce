package com.example.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ecommerce") // Reemplaza con el paquete donde están tus entidades
@EnableJpaRepositories(basePackages = "com.example.ecommerce") // Reemplaza con el paquete donde están tus repositorios JPA
public class EcommerceApplication {


    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
