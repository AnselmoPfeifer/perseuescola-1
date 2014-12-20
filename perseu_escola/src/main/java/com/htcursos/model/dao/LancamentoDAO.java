package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Lancamento;

@Transactional
@Repository
public class LancamentoDAO extends GenericDAO<Lancamento, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7171787145823321350L;

	public LancamentoDAO() {
		super(Lancamento.class);
	}

	

}
