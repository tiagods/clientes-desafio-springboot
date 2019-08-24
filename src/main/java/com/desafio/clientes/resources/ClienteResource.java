package com.desafio.clientes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import com.desafio.clientes.exception.ClienteNaoEncontradoException;
import com.desafio.clientes.model.Cliente;
import com.desafio.clientes.service.ClienteService;

@RestController
@RequestMapping(value="/api/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clientes;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(clientes.listar());
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> novo(@Valid @RequestBody Cliente cliente, BindingResult result) {
		if(result.hasErrors())
			throw new ClienteNaoEncontradoException("Falha na validação");
		cliente = clientes.salvar(cliente);
		//ao salvar um objeto informarei para o cliente onde ele vai localizar o recurso com codigo httpstatus 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value ="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Cliente cliente = clientes.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);			
	}
	@RequestMapping(value ="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors())
			throw new ClienteNaoEncontradoException("Falha na validação");
		cliente.setId(id);
		clientes.atualizar(cliente);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clientes.remover(id);
		return ResponseEntity.noContent().build();
	}
}
