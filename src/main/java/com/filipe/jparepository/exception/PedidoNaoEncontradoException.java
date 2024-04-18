package com.filipe.jparepository.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
        super("Pedido Não Encontrado.");
    }
}
