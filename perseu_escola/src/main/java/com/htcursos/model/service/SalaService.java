package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.SalaDAO;
import com.htcursos.model.entity.Sala;
@Service
public class SalaService extends GenericService<Sala, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2462001454856639377L;
	@Autowired
	private SalaDAO salaDAO;
	@Override
	public GenericDAO<Sala, Integer> getDao() {
		// TODO Auto-generated method stub
		return salaDAO;
	}

}
