package com.filipe.jparepository.service.impl;

import com.filipe.jparepository.domain.entity.Cliente;
import com.filipe.jparepository.domain.entity.ItemPedido;
import com.filipe.jparepository.domain.entity.Pedido;
import com.filipe.jparepository.domain.entity.Produto;
import com.filipe.jparepository.domain.enums.StatusPedido;
import com.filipe.jparepository.domain.repository.Clientes;
import com.filipe.jparepository.domain.repository.ItensPedido;
import com.filipe.jparepository.domain.repository.Pedidos;
import com.filipe.jparepository.domain.repository.Produtos;
import com.filipe.jparepository.exception.PedidoNaoEncontradoException;
import com.filipe.jparepository.exception.RegraNegocioException;
import com.filipe.jparepository.rest.dto.PedidoDTO;
import com.filipe.jparepository.rest.dto.ItemPedidoDTO;
import com.filipe.jparepository.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService { //Carrega as regras de negocio

    @Autowired
    private Pedidos pedidosRepository;

    @Autowired
    private Clientes clientesRepository;

    @Autowired
    private Produtos produtosRepository;

    @Autowired
    private ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de Cliente Inválido"));

        Pedido pedido = new Pedido();

        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        pedido.setStatus(StatusPedido.REALIZADO);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizarStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {

        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de Produto Inválido : " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;

                }).collect(Collectors.toList());
    }
}
