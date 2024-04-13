package com.filipe.jpa.domain.repositorio;

import com.filipe.jpa.domain.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class Clientes {

    @Autowired
    private EntityManager entityManager;

    @Transactional //Tomar cuidado com o Import (import org.springframework.transaction.annotation.Transactional)
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente); //Salvar no BD
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente); //Atualizar no BD
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional(readOnly = true) //Apenas Leitura , Otimiza a pesquisa.
    public List<Cliente> buscarPorNome(String nome) {
        String jpql = " select c from Cliente c where c.nome like :nome "; // o from tem q ser o nome da classe (Letra maiusculo)
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");

        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
        return entityManager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
