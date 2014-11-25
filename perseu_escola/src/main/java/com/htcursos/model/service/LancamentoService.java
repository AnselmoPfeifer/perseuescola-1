package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.LancamentoDAO;
import com.htcursos.model.entity.Lancamento;
@Service
public class LancamentoService extends GenericService<Lancamento, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4401118611681032157L;
	@Autowired
	private LancamentoDAO lancamentoDAO; 
	@Override
	public GenericDAO<Lancamento, Integer> getDao() {
		// TODO Auto-generated method stub
		return lancamentoDAO;
	}

}
