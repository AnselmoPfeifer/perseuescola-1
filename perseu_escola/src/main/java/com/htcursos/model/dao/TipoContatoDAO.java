package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.TipoContato;

@Transactional
@Repository
public class TipoContatoDAO extends GenericDAO<TipoContato, Integer> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6057719229717065561L;

	public TipoContatoDAO() {
		super(TipoContato.class);
	}

}
