package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.CidadeDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Cidade;
@Service
public class CidadeService extends GenericService<Cidade, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3878963254279975461L;
	@Autowired
	private CidadeDAO cidadeDAO;
	@Override
	public GenericDAO<Cidade, Integer> getDao() {
		// TODO Auto-generated method stub
		return cidadeDAO;
	}

}
