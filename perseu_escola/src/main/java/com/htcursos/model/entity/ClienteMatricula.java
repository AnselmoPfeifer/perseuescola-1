package com.htcursos.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.htcursos.model.enums.TipoContratacaoEnum;

@Entity
public class ClienteMatricula implements Serializable, Modelo<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_cliente_matricula", sequenceName = "seq_cliente_matricula", initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "seq_cliente_matricula", strategy = GenerationType.AUTO)
	private Integer id;
	@JoinColumn
	@ManyToOne
	private Cliente cliente;
	@JoinColumn
	@ManyToOne
	private Matricula matricula;
	@Enumerated(EnumType.ORDINAL)
	private TipoContratacaoEnum tipoContratacao;
	
	private Boolean vaiUsarNotebook=false;

	public ClienteMatricula(Cliente cliente, Matricula matricula) {
		setCliente(cliente);
		setMatricula(matricula);
	}

	public ClienteMatricula() {
		// TODO Auto-generated constructor stub
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}


	public TipoContratacaoEnum getTipoContratacao() {
		return tipoContratacao;
	}

	public void setTipoContratacao(TipoContratacaoEnum tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result
				+ ((tipoContratacao == null) ? 0 : tipoContratacao.hashCode());
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
		ClienteMatricula other = (ClienteMatricula) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (tipoContratacao != other.tipoContratacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteMatricula [id=" + id + ", cliente=" + cliente
				+ ", matricula=" + matricula + ", tipoContratacao="
				+ tipoContratacao + "]";
	}

	public Boolean getVaiUsarNotebook() {
		return vaiUsarNotebook;
	}
	
	public String getVaiUsarNotebookFmt() {
		return vaiUsarNotebook==true?"Sim":"Não";
	}

	public void setVaiUsarNotebook(Boolean vaiUsarNotebook) {
		this.vaiUsarNotebook = vaiUsarNotebook;
	}



}
