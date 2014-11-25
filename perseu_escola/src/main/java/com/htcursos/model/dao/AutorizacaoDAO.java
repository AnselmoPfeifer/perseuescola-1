package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.htcursos.model.entity.Autorizacao;
@Repository
public class AutorizacaoDAO extends GenericDAO<Autorizacao, Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8643123134335694085L;

	public AutorizacaoDAO() {
		super(Autorizacao.class);
		// TODO Auto-generated constructor stub
	}

}
