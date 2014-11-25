package com.htcursos.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
/**
 * classe criada para permitir que somente clientes matriculados entrem no calendario
 * @author User
 *
 */
public class ClienteMatriculaCalendario implements Serializable, Modelo<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator (name="seq_cliente_calendario",sequenceName="seq_cliente_calendario", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_cliente_calendario", strategy=GenerationType.AUTO)
	private Integer id;
	@JoinColumn
	@ManyToOne
	private Calendario calendario;
	@JoinColumn
	@ManyToOne
	private ClienteMatricula clienteMatricula;
	private Integer notebook;
	private Integer certificadoimpresso;
	private Double nota;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Integer checkin;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ClienteMatriculaCalendario() {
		super();
	}
	public Calendario getCalendario() {
		return calendario;
	}
	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	public Integer getNotebook() {
		return notebook;
	}
	public ClienteMatricula getClienteMatricula() {
		return clienteMatricula;
	}
	public void setClienteMatricula(ClienteMatricula clienteMatricula) {
		this.clienteMatricula = clienteMatricula;
	}
	public void setNotebook(Integer notebook) {
		this.notebook = notebook;
	}
	public Integer getCertificadoimpresso() {
		return certificadoimpresso;
	}
	public void setCertificadoimpresso(Integer certificadoimpresso) {
		this.certificadoimpresso = certificadoimpresso;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getCheckin() {
		return checkin;
	}
	public void setCheckin(Integer checkin) {
		this.checkin = checkin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((clienteMatricula == null) ? 0 : clienteMatricula.hashCode());
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
		ClienteMatriculaCalendario other = (ClienteMatriculaCalendario) obj;
		if (clienteMatricula == null) {
			if (other.clienteMatricula != null)
				return false;
		} else if (!clienteMatricula.equals(other.clienteMatricula))
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
		return "ClienteMatriculaCalendario [id=" + id + ", calendario="
				+ calendario + ", clienteMatricula=" + clienteMatricula
				+ ", notebook=" + notebook + ", certificadoimpresso="
				+ certificadoimpresso + ", nota=" + nota + ", data=" + data
				+ ", checkin=" + checkin + "]";
	}
	
	
	
	
}
