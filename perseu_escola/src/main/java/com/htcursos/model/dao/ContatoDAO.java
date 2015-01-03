package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Contato;

@Transactional
@Repository
public class ContatoDAO extends GenericDAO<Contato, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1863467022983446170L;

	public ContatoDAO(){
		super(Contato.class);
	}

}
