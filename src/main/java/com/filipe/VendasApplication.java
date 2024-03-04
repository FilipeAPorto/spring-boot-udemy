package com.filipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // Iniciar SpringBoot
@RestController // ira explicar mais pra frente
public class VendasApplication {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World - Filipe";
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args); //Argumento para iniciar Spring Boot
    }
}
