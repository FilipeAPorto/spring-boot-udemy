package com.filipe.jdbc.domain;

import com.filipe.jdbc.domain.entity.Cliente;
import com.filipe.jdbc.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication // Anotation para Iniciar SpringBoot
public class VendasJDBCApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {

            System.out.println("Salvando Clientes");
            clientes.salvar(new Cliente("Filipe"));
            clientes.salvar(new Cliente("Andressa"));

            List<Cliente> todosClientes = clientes.obterTodos(); //Cria uma Lista na aplicação com o  todos os clientes
            todosClientes.forEach(System.out::println);

            System.out.println("\ntualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos(); //Puxa do BD e seta na list os nomes que estão no BD
            todosClientes.forEach(System.out::println);

            System.out.println("\nBuscando Clientes");
            clientes.buscarPorNome("il").forEach(System.out::println);

            System.out.println("\nDeletando Clientes");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado !!");
            } else {
                todosClientes.forEach(System.out::println);
            }

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasJDBCApplication.class, args);
        //Necessário para iniciar a aplicação, colocando como argumento o nome
        //da classe desta propria aplicação "VendasApplication"
    }
}