package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.PagamentoDAO;
import com.htcursos.model.entity.Pagamento;
@Service
public class PagamentoService extends GenericService<Pagamento, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7318876896732733684L;
	@Autowired
	private PagamentoDAO pagamentoDAO;
	@Override
	public GenericDAO<Pagamento, Integer> getDao() {
		// TODO Auto-generated method stub
		return pagamentoDAO;
	}

}
