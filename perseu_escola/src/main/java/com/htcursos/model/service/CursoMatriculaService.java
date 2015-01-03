package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.CursoMatriculaDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.CursoMatricula;
@Service
public class CursoMatriculaService extends GenericService<CursoMatricula, Integer> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012026238867213845L;
	@Autowired
	private CursoMatriculaDAO cursoMatriculaDAO;
	
	@Override
	public GenericDAO<CursoMatricula, Integer> getDao() {
		// TODO Auto-generated method stub
		return cursoMatriculaDAO;
	}

}
