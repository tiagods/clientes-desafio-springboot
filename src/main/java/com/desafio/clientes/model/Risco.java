package com.desafio.clientes.model;

public enum Risco{
	A("A"),B("B"),C("C");
	private String descricao;
	Risco(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}

}
