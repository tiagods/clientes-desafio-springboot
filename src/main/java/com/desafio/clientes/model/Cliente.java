package com.desafio.clientes.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;
	
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.01", message="O valor do credito deve ser maior que R$ 0,01")
	@DecimalMax(value = "9999999.99", message="O valor do credito deve ser menor que R$ 9.999.999,99")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal credito;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Enumerated(value= EnumType.STRING)
	private Risco risco = Risco.A;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar criadoEm;
	
	@Transient
	private String moedaParaReal;
	

	@PrePersist
	private void onCreated() {
		setCriadoEm(Calendar.getInstance());
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the credito
	 */
	public BigDecimal getCredito() {
		return credito;
	}
	/**
	 * @param credito the credito to set
	 */
	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}
	/**
	 * @return the risco
	 */
	public Risco getRisco() {
		return risco;
	}
	/**
	 * @param risco the risco to set
	 */
	public void setRisco(Risco risco) {
		this.risco = risco;
	}
	
	public void setCriadoEm(Calendar criadoEm) {
		this.criadoEm = criadoEm;
	}
	/**
	 * @return the moedaParaReal
	 */
	public String getMoedaParaReal() {
		Locale locale = new Locale("pt","br");
		NumberFormat number = NumberFormat.getInstance(locale);
		number.setMinimumFractionDigits(2); //Seta o número mínimo de casa decimal
	    number.setMaximumFractionDigits(2); //Seta o número máximo de casa decimal
		moedaParaReal =  number.format(credito);
		return moedaParaReal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credito == null) ? 0 : credito.hashCode());
		result = prime * result + ((criadoEm == null) ? 0 : criadoEm.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((moedaParaReal == null) ? 0 : moedaParaReal.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((risco == null) ? 0 : risco.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (credito == null) {
			if (other.credito != null)
				return false;
		} else if (!credito.equals(other.credito))
			return false;
		if (criadoEm == null) {
			if (other.criadoEm != null)
				return false;
		} else if (!criadoEm.equals(other.criadoEm))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (moedaParaReal == null) {
			if (other.moedaParaReal != null)
				return false;
		} else if (!moedaParaReal.equals(other.moedaParaReal))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (risco != other.risco)
			return false;
		return true;
	}
	
}
