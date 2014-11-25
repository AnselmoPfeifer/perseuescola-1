package com.htcursos.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.UsuarioDAO;
import com.htcursos.model.entity.Usuario;

@Service
public class UsuarioService extends GenericService<Usuario, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8685340054586410106L;
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public GenericDAO<Usuario, Integer> getDao() {		
		return usuarioDAO;
	}
	
	public Usuario buscarPorUserName(String userName){
		return usuarioDAO.buscarPorUserName(userName);	
	}	
}
