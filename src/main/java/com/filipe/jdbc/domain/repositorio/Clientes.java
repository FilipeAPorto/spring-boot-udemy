package com.filipe.jdbc.domain.repositorio;

import com.filipe.jdbc.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?) ";
    private static String SELECT_ALL = "select * from cliente ";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";
    private static String SELECT_POR_NOME = "select * from cliente where nome like ? ";


    @Autowired
    JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(), //Insere o primeiro parâmetro do Script que é o nome
                cliente.getId()}); // Depois insere o segundo parâmetro do Script que é o ID
        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());  //Pega o ID do cliente settado e joga pro metodo abaixo
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id}); // Pega o ID e insere no Script do SQL
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(SELECT_POR_NOME,
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }


    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private static RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
