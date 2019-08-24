package com.desafio.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.clientes.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{
	List<Cliente> findByNomeContaining(String nome);
}
