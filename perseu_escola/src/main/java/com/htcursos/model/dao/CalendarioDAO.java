package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Calendario;

@Transactional
@Repository
public class CalendarioDAO extends GenericDAO<Calendario, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6724616757718393451L;

	public CalendarioDAO() {
		super(Calendario.class);
	}

}
