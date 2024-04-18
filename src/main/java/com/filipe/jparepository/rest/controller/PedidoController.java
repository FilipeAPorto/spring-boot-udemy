package com.filipe.jparepository.rest.controller;

import com.filipe.jparepository.domain.entity.ItemPedido;
import com.filipe.jparepository.domain.entity.Pedido;
import com.filipe.jparepository.domain.enums.StatusPedido;
import com.filipe.jparepository.rest.dto.AtualizacaoStatusPedidoDTO;
import com.filipe.jparepository.rest.dto.InformacoesItemPedidoDTO;
import com.filipe.jparepository.rest.dto.InformacoesPedidoDTO;
import com.filipe.jparepository.rest.dto.PedidoDTO;
import com.filipe.jparepository.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    public PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer cadastrarPedido(@RequestBody @Valid PedidoDTO dto) { //Integer pq ele vai retornar somente o ID do pedido gerado

        Pedido pedidoSalvo = pedidoService.salvar(dto);
        return pedidoSalvo.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encotrado!"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    private void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {

        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
    }


    private InformacoesPedidoDTO converter(Pedido pedido) {

        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }


    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }


}
