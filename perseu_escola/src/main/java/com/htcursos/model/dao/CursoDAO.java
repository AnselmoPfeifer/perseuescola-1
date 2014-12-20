package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Curso;


@Transactional
@Repository
public class CursoDAO extends GenericDAO<Curso, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2610215778295018680L;

	public CursoDAO() {
	super(Curso.class);
	}

}
