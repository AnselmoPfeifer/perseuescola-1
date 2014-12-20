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

import com.htcursos.model.enums.TurnoEnum;

@Entity
public class Calendario implements Serializable, Modelo<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name="seq_calendario",sequenceName="seq_calendario", initialValue=1, allocationSize=10)
	@GeneratedValue(generator="seq_calendario", strategy=GenerationType.AUTO)
	private Integer id;
	@JoinColumn
	@ManyToOne
	private Curso curso;
	@JoinColumn
	@ManyToOne
	private Professor professor;
	@Temporal(TemporalType.DATE)
	private Date datainicio;
	@Temporal(TemporalType.DATE)
	private Date datafim;
	private Integer status;
	/**
	 * EX: segunda quarta e sexta
	 */
	private String diasSemana;
	private TurnoEnum turno;
	@JoinColumn
	@ManyToOne
	private Sala sala;
	
	
	public String getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}
	public TurnoEnum getTurno() {
		return turno;
	}
	public void setTurno(TurnoEnum turno) {
		this.turno = turno;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Calendario() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Date getDatainicio() {
		return datainicio;
	}
	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}
	public Date getDatafim() {
		return datafim;
	}
	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
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
		Calendario other = (Calendario) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
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
		return "Calendario [id=" + id + ", curso=" + curso + ", professor="
				+ professor + ", datainicio=" + datainicio + ", datafim="
				+ datafim + ", status=" + status + ", diasSemana=" + diasSemana
				+ ", turno=" + turno + ", sala=" + sala + "]";
	}
	
	

}
