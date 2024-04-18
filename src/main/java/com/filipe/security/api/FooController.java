package com.filipe.security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController { //Criar End-Points para criar scrpits para testes

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){
        return ResponseEntity.ok("Public route OK!");

    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(){
        return ResponseEntity.ok("Private route OK!");
    }

}
