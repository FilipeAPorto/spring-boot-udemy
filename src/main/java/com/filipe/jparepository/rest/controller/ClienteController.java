package com.filipe.jparepository.rest.controller;

import com.filipe.jparepository.domain.entity.Cliente;
import com.filipe.jparepository.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired // -> Posso usar o AutoWired ou criar um contrutor setando o clientes
    private Clientes clienteRepository; //declarando a interface Clientes do repository


    @GetMapping("/{id}")
    public Cliente obterClientePorId(@PathVariable Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));//Retorna mensagem de Erro na execução
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Retorna o Status Creado na web
    public Cliente salvarClientes(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // NoContent não retorna nada só mostra msg de sucesso
    public void deletarCliente(@PathVariable Integer id) {
        clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarIntegralmenteCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {

        clienteRepository.findById(id).map(c -> {
            cliente.setId(c.getId());
            clienteRepository.save(cliente);
            return c;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> listarClientes(Cliente filtroCliente) {
        ExampleMatcher matcher = ExampleMatcher.
                matching().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtroCliente, matcher);

        return clienteRepository.findAll(example);
    }
}
