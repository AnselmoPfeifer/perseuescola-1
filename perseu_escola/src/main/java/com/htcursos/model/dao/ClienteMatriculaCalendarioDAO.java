package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.ClienteMatriculaCalendario;

@Transactional
@Repository
public class ClienteMatriculaCalendarioDAO extends GenericDAO<ClienteMatriculaCalendario, Integer> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5444756071400268812L;

	public ClienteMatriculaCalendarioDAO() {
		super(ClienteMatriculaCalendario.class);
	}

}
