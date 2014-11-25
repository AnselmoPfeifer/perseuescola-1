package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.ClienteMatricula;

@Transactional
@Repository
public class ClienteMatriculaDAO extends GenericDAO<ClienteMatricula, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3606674965571357009L;

	public ClienteMatriculaDAO(){
		super(ClienteMatricula.class);
	}

}
