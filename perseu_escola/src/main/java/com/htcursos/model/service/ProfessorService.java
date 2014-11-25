package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.ProfessorDAO;
import com.htcursos.model.entity.Professor;
@Service
public class ProfessorService extends GenericService<Professor, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9034531948045950501L;
	@Autowired
	private ProfessorDAO professorDAO;
	@Override
	public GenericDAO<Professor, Integer> getDao() {
		// TODO Auto-generated method stub
		return professorDAO;
	}

}
