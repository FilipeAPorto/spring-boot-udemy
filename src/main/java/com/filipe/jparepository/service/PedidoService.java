package com.filipe.jparepository.service;

import com.filipe.jparepository.domain.entity.Pedido;
import com.filipe.jparepository.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);
}
