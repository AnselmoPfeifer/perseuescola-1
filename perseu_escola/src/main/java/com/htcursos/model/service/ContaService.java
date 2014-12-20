package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.ContaDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Conta;
@Service
public class ContaService extends GenericService<Conta, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -901747609136412379L;
	@Autowired
	private ContaDAO contaDAO;
	@Override
	public GenericDAO<Conta, Integer> getDao() {
		return contaDAO;
	}
	public Conta buscarPorCodigoReduzido(Integer codigoReduzido) {
		return contaDAO.buscarPorCodigoReduzido(codigoReduzido);
		
	}

}
