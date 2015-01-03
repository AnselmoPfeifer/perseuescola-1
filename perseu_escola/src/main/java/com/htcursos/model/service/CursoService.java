package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.CursoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Curso;
@Service
public class CursoService extends GenericService<Curso, Integer>  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1804055002128511771L;
	@Autowired
	private CursoDAO cursoDao;
	@Override
	public GenericDAO<Curso, Integer> getDao() {
		// TODO Auto-generated method stub
		return cursoDao;
	}

}
