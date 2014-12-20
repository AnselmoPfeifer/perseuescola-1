package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.UfDAO;
import com.htcursos.model.entity.Uf;
@Service
public class UfService extends GenericService<Uf, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2203423180345125932L;
	@Autowired
	private UfDAO ufDAO;
	@Override
	public GenericDAO<Uf, Integer> getDao() {
		// TODO Auto-generated method stub
		return ufDAO;
	}

}
