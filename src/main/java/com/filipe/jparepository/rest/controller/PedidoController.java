package com.filipe.jparepository.rest.controller;

import com.filipe.jparepository.domain.entity.Pedido;
import com.filipe.jparepository.rest.dto.PedidoDTO;
import com.filipe.jparepository.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    public PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer cadastrarPedido(@RequestBody PedidoDTO dto) { //Integer pq ele vai retornar somente o ID do pedido gerado

        Pedido pedidoSalvo = pedidoService.salvar(dto);
        return pedidoSalvo.getId();
    }


}
