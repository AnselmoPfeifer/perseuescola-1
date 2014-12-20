package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.CalendarioDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Calendario;
@Service
public class CalendarioService extends GenericService<Calendario, Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3847167075616755227L;
	@Autowired
	CalendarioDAO calendarioDAO;

	@Override
	public GenericDAO<Calendario, Integer> getDao() {
		// TODO Auto-generated method stub
		return calendarioDAO;
	}

}
