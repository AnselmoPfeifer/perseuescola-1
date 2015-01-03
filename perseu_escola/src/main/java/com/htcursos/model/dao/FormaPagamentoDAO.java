package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.FormaPagamento;

@Transactional
@Repository
public class FormaPagamentoDAO extends GenericDAO<FormaPagamento, Integer> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6789995240250799405L;

	public FormaPagamentoDAO() {
	super(FormaPagamento.class);
	}

}
