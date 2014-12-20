package com.htcursos.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Matricula;

@Transactional
@Repository
public class MatriculaDAO extends GenericDAO<Matricula, Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6917618941842998685L;

	public MatriculaDAO() {
	super(Matricula.class);
	}

}
