package com.filipe.jparepository;

import com.filipe.jparepository.domain.entity.Cliente;
import com.filipe.jparepository.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // Anotation para Iniciar SpringBoot
public class VendasJPARepositoryApplication {


    public static void main(String[] args) {
        SpringApplication.run(VendasJPARepositoryApplication.class, args);
        //Necessário para iniciar a aplicação, colocando como argumento o nome
        //da classe desta propria aplicação "VendasApplication"
    }
}