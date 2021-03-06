package com.htcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;


@Embeddable
public class Endereco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6469084389749038853L;
	@NotEmpty(message="Estado Obrigatório")
	private String estado="";
	@NotEmpty(message="Cidade Obrigatório")
	private String  cidade="";
	@NotEmpty(message="Logradouro Obrigatório")
    private String logradouro="";
	@NotEmpty(message="Bairro Obrigatório")
    private String bairro="";
    private String complemento="";
    @NotEmpty(message="Número Obrigatório")
    private String numero="";
    @NotEmpty(message="Cep Obrigatório")
    private String cep="";
    private String tipo_logradouro="";
    
	
	public String getTipo_logradouro() {
		return tipo_logradouro;
	}
	public void setTipo_logradouro(String tipo_logradouro) {
		this.tipo_logradouro = tipo_logradouro;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return "Endereco [cidade=" + cidade + ", logradouro=" + logradouro
				+ ", bairro=" + bairro + ", complemento=" + complemento
				+ ", numero=" + numero + ", cep=" + cep + ", tipo_logradouro="
				+ tipo_logradouro + "]";
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	   

}
