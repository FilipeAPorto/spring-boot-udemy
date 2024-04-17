package com.filipe.jparepository.rest.controller;

import com.filipe.jparepository.domain.entity.Produto;
import com.filipe.jparepository.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private Produtos produtoRepository;

    @GetMapping("/{id}")
    public Produto obterProdutoPorId(@PathVariable Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto Não Encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Integer id) {
        produtoRepository.findById(id).map(produto -> {
            produtoRepository.delete(produto);
            return produto;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto Não Encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        produtoRepository.findById(id).map(p -> {
            produto.setId(p.getId());
            produtoRepository.save(produto);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto Não Encontrado"));
    }

    @GetMapping
    public List<Produto> listarProdutos(Produto filtroProduto) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtroProduto,matcher);

        return produtoRepository.findAll(example);
    }
}
