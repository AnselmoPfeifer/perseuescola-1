package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Professor;

@Transactional
@Repository
public class ProfessorDAO extends GenericDAO<Professor, Integer> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 518419186327284461L;

	public ProfessorDAO() {
		super(Professor.class);

	}

}