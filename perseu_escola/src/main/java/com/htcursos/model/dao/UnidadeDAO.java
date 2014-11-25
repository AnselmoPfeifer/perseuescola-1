package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Unidade;

@Transactional
@Repository
public class UnidadeDAO extends GenericDAO<Unidade, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5447794401549285844L;

	public UnidadeDAO() {
	super(Unidade.class);
	}
}
