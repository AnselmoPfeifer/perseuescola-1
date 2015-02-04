package com.htcursos.model.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.ClienteMatriculaDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Cliente;
import com.htcursos.model.entity.ClienteMatricula;
import com.htcursos.model.entity.Matricula;
@Service
public class ClienteMatriculaService extends GenericService<ClienteMatricula, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3797885274467442397L;
	@Autowired
	private ClienteMatriculaDAO clienteMatriculaDAO;
	
	@Override
	public GenericDAO<ClienteMatricula, Integer> getDao() {
		// TODO Auto-generated method stub
		return clienteMatriculaDAO;
	}

	public List<Matricula> buscarMatriculas(Cliente cliente) throws NoResultException{
		
		return clienteMatriculaDAO.buscarMatriculas(cliente);

	}

}
