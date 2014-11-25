package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.ContatoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Contato;
@Service
public class ContatoService extends GenericService<Contato, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -630826211616525250L;
	@Autowired
	private ContatoDAO contatoDAO;
	@Override
	public GenericDAO<Contato, Integer> getDao() {
		// TODO Auto-generated method stub
		return contatoDAO;
	}

}
