package com.htcursos.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Anexo;

@Transactional
@Repository
public class AnexoDAO extends GenericDAO<Anexo, Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnexoDAO() {
	super(Anexo.class);
	}
}
