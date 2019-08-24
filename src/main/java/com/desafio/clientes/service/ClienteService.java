package com.desafio.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.desafio.clientes.exception.ClienteNaoEncontradoException;
import com.desafio.clientes.model.Cliente;
import com.desafio.clientes.repository.Clientes;
import com.desafio.clientes.repository.filter.ClienteFilter;

@Service
public class ClienteService {
	@Autowired
	private Clientes clientes;
	
	public List<Cliente> filtrar(ClienteFilter filter) {
		String nome = filter.getNome() == null ? "%" : filter.getNome();
		return clientes.findByNomeContaining(nome);
	}
	
	public List<Cliente> listar() {
		return clientes.findAll();
	}
	public Cliente buscar(Long id) {
		Cliente cliente = clientes.findOne(id);
		if(cliente==null) {
			throw new ClienteNaoEncontradoException("O cliente não pode ser encontrado");
		}
		return cliente;
	}
	public Cliente salvar(Cliente cliente) {
		cliente.setId(null);
		return clientes.save(cliente);
	}
	public void remover(Long id) {
		try{
			clientes.delete(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException("O cliente não pode ser encontrado");
		}
	}
	public void atualizar(Cliente cliente) {
		verificarExistencia(cliente);
		clientes.save(cliente);
	}
	
	public void verificarExistencia(Cliente cliente) {
		buscar(cliente.getId());
	}
	
	
	
}
