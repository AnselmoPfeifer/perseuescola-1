package com.htcursos.model.dao;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Usuario;

@Transactional
@Repository
public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4344386590031442561L;

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario buscarPorUserName(String userName) {
		Query consulta = em.createQuery("Select u from Usuario u where u.username=:userName");
		consulta.setParameter("userName", userName);	
		consulta.setMaxResults(1);
		return (Usuario)consulta.getSingleResult();		
	}
}
