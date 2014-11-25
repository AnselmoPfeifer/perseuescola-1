package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Grupo;

@Transactional
@Repository
public class GrupoDAO extends GenericDAO<Grupo, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6760552967815151038L;

	public GrupoDAO() {
		super(Grupo.class);
	}

}
