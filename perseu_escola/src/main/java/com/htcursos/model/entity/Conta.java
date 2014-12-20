package com.htcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Conta implements Serializable, Modelo<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name="seq_conta",sequenceName="seq_conta", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_conta", strategy=GenerationType.AUTO)
	private Integer id;
	private Boolean ativo;
	private String classificacao;
	private Integer codigoReduzido;
	private String descricao;
	private Integer grau;
	
	/**
	 * Metodo que retorna a concatenaçao do numero + o nome
	 * @return
	 */
	public String getContaFmt(){
		return codigoReduzido+" "+descricao; 
	}
	
	//getter and setters
	public Conta() {
		super();
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Integer getCodigoReduzido() {
		return codigoReduzido;
	}
	public void setCodigoReduzido(Integer codigoReduzido) {
		this.codigoReduzido = codigoReduzido;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getGrau() {
		return grau;
	}
	public void setGrau(Integer grau) {
		this.grau = grau;
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
		Conta other = (Conta) obj;
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
		return "Conta [id=" + id + ", ativo=" + ativo + ", classificacao="
				+ classificacao + ", codigoReduzido=" + codigoReduzido
				+ ", descricao=" + descricao + ", grau=" + grau + "]";
	}
	
	

	
}
