package com.htcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class FormaPagamento implements Serializable ,Modelo<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name="seq_formaPagamento",sequenceName="seq_formaPagamento", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_formaPagamento", strategy=GenerationType.AUTO)
	private Integer id;
	private String descricao;
	@JoinColumn
	@ManyToOne
	private Conta conta;
	
	private Boolean cobrancaPropria; 


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagamento other = (FormaPagamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "FormaPagamento [id=" + id + ", descricao=" + descricao
				+ ", conta=" + conta + "]";
	}
	public Boolean getCobrancaPropria() {
		return cobrancaPropria;
	}
	public void setCobrancaPropria(Boolean cobrancaPropria) {
		this.cobrancaPropria = cobrancaPropria;
	}
	
	
	
	
}
