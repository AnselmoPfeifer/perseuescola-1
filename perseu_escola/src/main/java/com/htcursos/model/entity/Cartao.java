package com.htcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Cartao implements Serializable, Modelo<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name="seq_cartao",sequenceName="seq_cartao", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_cartao", strategy=GenerationType.AUTO)
	private Integer id;
	private String nomeimpresso;
	private String numero;
	private Integer mesvalidade;
	private Integer anovalidade;
	private String codigoseguranca;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeimpresso() {
		return nomeimpresso;
	}
	public void setNomeimpresso(String nomeimpresso) {
		this.nomeimpresso = nomeimpresso;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getMesvalidade() {
		return mesvalidade;
	}
	public void setMesvalidade(Integer mesvalidade) {
		this.mesvalidade = mesvalidade;
	}
	public Integer getAnovalidade() {
		return anovalidade;
	}
	public void setAnovalidade(Integer anovalidade) {
		this.anovalidade = anovalidade;
	}
	public String getCodigoseguranca() {
		return codigoseguranca;
	}
	public void setCodigoseguranca(String codigoseguranca) {
		this.codigoseguranca = codigoseguranca;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomeimpresso == null) ? 0 : nomeimpresso.hashCode());
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
		Cartao other = (Cartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeimpresso == null) {
			if (other.nomeimpresso != null)
				return false;
		} else if (!nomeimpresso.equals(other.nomeimpresso))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Cartao [id=" + id + ", nomeimpresso=" + nomeimpresso
				+ ", numero=" + numero + ", mesvalidade=" + mesvalidade
				+ ", anovalidade=" + anovalidade + ", codigoseguranca="
				+ codigoseguranca + "]";
	}
	
	
	
}
