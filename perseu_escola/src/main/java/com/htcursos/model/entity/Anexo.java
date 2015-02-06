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
public class Anexo implements Serializable, Modelo<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_anexo", sequenceName = "seq_anexo", allocationSize = 1, initialValue = 30)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anexo")
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Matricula matricula;
	//CAMINHO DA PASTA UPLOAD
	private String caminho;
	//NOME GERADO
	private String nomeGerado;
	//NOME ORIGINAL DO ARQUIVO
	private String nomeOriginal;
	
	//GETTERS E SETTERS
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public String getNomeGerado() {
		return nomeGerado;
	}
	public void setNomeGerado(String nomeGerado) {
		this.nomeGerado = nomeGerado;
	}
	public String getNomeOriginal() {
		return nomeOriginal;
	}
	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}
	@Override
	public Integer getId() {
		return null;
	}
}
