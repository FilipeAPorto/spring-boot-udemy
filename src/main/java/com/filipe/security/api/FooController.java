package com.filipe.security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController { //Criar End-Points para criar scrpits para testes

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public route OK!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication) {
        return ResponseEntity.ok("Private route OK! \nUsuário Conectado : " + authentication.getName());
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") //Ir em cada uma das URL e definir o que cada é permissivel
    public ResponseEntity<String> adminRoute() {
        return ResponseEntity.ok("Admin route OK!");
    }

}
