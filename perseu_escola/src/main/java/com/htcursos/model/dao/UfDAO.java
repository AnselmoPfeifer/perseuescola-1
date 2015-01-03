package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Uf;

@Transactional
@Repository
public class UfDAO extends GenericDAO<Uf, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4582211489650207202L;

	public UfDAO(){
		super(Uf.class);
	}
	
}
