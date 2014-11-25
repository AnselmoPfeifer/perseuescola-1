package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Cartao;

@Transactional
@Repository
public class CartaoDAO extends GenericDAO<Cartao, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7112333471793577936L;

	public CartaoDAO() {
	super(Cartao.class);
	}

}
