package com.htcursos.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Anexo {

	@Id
	@SequenceGenerator(name = "seq_anexo", sequenceName = "seq_anexo", allocationSize = 1, initialValue = 30)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anexo")
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn
	private Matricula matricula;
	//CAMINHO DA PASTA UPLOAD
	private String caminho;
	//NOME GERADO
	private String nomeGerado;
	//NOME ORIGINAL DO ARQUIVO
	private String tituloOriginal;

}
