package com.filipe.jparepository.service;

import com.filipe.jparepository.domain.entity.Pedido;
import com.filipe.jparepository.domain.enums.StatusPedido;
import com.filipe.jparepository.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id , StatusPedido statusPedido);
}
