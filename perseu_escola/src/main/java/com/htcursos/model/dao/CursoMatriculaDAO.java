package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.CursoMatricula;

@Transactional
@Repository
public class CursoMatriculaDAO extends GenericDAO<CursoMatricula, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -554175813208782270L;

	public CursoMatriculaDAO(){
		super(CursoMatricula.class);
	}

}
