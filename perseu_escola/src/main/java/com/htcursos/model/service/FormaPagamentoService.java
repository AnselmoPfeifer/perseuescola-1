package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.FormaPagamentoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.FormaPagamento;
@Service
public class FormaPagamentoService extends GenericService<FormaPagamento, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6046304113893882601L;
	@Autowired
	private FormaPagamentoDAO formaPagamentoDAO;
	@Override
	public GenericDAO<FormaPagamento, Integer> getDao() {
		// TODO Auto-generated method stub
		return formaPagamentoDAO;
	}

}
