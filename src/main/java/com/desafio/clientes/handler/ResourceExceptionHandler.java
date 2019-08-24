package com.desafio.clientes.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio.clientes.exception.ClienteNaoEncontradoException;
import com.desafio.clientes.model.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleContatoNaoEncontradoException
	(ClienteNaoEncontradoException e, HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O cliente nao pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://meudesafio.com.br/erros");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
