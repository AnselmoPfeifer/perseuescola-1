package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.UnidadeDAO;
import com.htcursos.model.entity.Unidade;
@Service
public class UnidadeService extends GenericService<Unidade, Integer> implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -2791527809415371985L;
@Autowired
	private UnidadeDAO unidadeDAO;
	@Override
	public GenericDAO<Unidade, Integer> getDao() {
		// TODO Auto-generated method stub
		return unidadeDAO;
	}

}
