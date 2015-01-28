package com.htcursos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.htcursos.model.util.FormataUtil;


@Entity
public class CursoMatricula implements Serializable, Modelo<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator (name="seq_curso_matricula",sequenceName="seq_curso_matricula", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_curso_matricula", strategy=GenerationType.AUTO)
	private Integer id;
	@JoinColumn
	@ManyToOne
	private Curso curso;
	@JoinColumn
	@ManyToOne
	private Matricula matricula;
	
	private BigDecimal valor;
	//Se o curso já foi realizado ou não 
	private Boolean realizado;
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
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
		CursoMatricula other = (CursoMatricula) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CursoMatricula [id=" + id + ", curso=" + curso + ", matricula="
				+ matricula + "]";
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getValorFmt() {

		return FormataUtil.formataMoedaBrasil(valor);
	}
	public Boolean getRealizado() {
		return realizado;
	}
	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}


	
	
}
