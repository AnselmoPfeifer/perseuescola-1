package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Pagamento;

@Transactional
@Repository
public class PagamentoDAO extends GenericDAO<Pagamento, Integer> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1702306286511163810L;

	public PagamentoDAO() {
		super(Pagamento.class);
		}
	

}
