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

/* @Bean
 public CommandLineRunner init(
         @Autowired Clientes clientes,
         @Autowired Pedidos pedidos) {
     return args -> {

         System.out.println("\nSalvando Clientes");
         Cliente filipe = new Cliente("Filipe");
         clientes.save(filipe);

         List<Cliente> todosClientes = clientes.findAll(); //Cria uma Lista na aplicação com o  todos os clientes
         todosClientes.forEach(System.out::println);

         System.out.println("\nCriando Pedido");
         Pedido pedido = new Pedido();
         pedido.setCliente(filipe);
         pedido.setDataPedido(LocalDate.now());
         pedido.setTotal(BigDecimal.valueOf(100));

         pedidos.save(pedido);

         *//*Cliente cliente = clientes.findClienteFetchPedidos(filipe.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());*//*

            pedidos.findByCliente(filipe).forEach(System.out::println);


*//*
            System.out.println("\nBuscando Clientes");
            todosClientes = clientes.findByNomeContaining("lipe");
            todosClientes.forEach(System.out::println);*//*

 *//*System.out.println("\nAtualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });

            todosClientes = clientes.findAll(); //Puxa do BD e seta na list os nomes que estão no BD
            todosClientes.forEach(System.out::println);*//*

 *//*System.out.println("\nDeletando Clientes");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });*//*
 *//*
            todosClientes = clientes.findAll();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado !!");
            } else {
                todosClientes.forEach(System.out::println);
            }*//*

        };*/