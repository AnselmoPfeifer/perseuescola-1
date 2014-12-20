package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.GrupoDAO;
import com.htcursos.model.entity.Grupo;
@Service
public class GrupoService extends GenericService<Grupo, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154167674243498164L;
	@Autowired
	private GrupoDAO grupoDAO;
	@Override
	public GenericDAO<Grupo, Integer> getDao() {
		// TODO Auto-generated method stub
		return grupoDAO;
	}

}
