package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.ClienteMatriculaCalendarioDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.ClienteMatriculaCalendario;
@Service
public class ClienteMatriculaCalendarioService extends GenericService<ClienteMatriculaCalendario, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2587606687881803768L;
	@Autowired
	private ClienteMatriculaCalendarioDAO clienteMatriculaCalendarioDAO;
	@Override
	public GenericDAO<ClienteMatriculaCalendario, Integer> getDao() {
		// TODO Auto-generated method stub
		return clienteMatriculaCalendarioDAO;
	}

}
