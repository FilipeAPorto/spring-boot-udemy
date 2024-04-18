package com.filipe.jparepository.rest.controller;

import com.filipe.jparepository.exception.PedidoNaoEncontradoException;
import com.filipe.jparepository.exception.RegraNegocioException;
import com.filipe.jparepository.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class) //Manupula qual exceção irá aparecer na API caso o usuario insira info incorretas
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Aparece essa Exception
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){

        String mensagemErro = ex.getMessage();

        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }
}

