package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Sala;

@Transactional
@Repository
public class SalaDAO extends GenericDAO<Sala, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8874778596162634410L;

	public SalaDAO() {
		super(Sala.class);
	}

}
