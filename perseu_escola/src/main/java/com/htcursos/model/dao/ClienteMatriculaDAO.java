package com.htcursos.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Cliente;
import com.htcursos.model.entity.ClienteMatricula;
import com.htcursos.model.entity.Matricula;

@Transactional
@Repository
public class ClienteMatriculaDAO extends GenericDAO<ClienteMatricula, Integer>
		implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3606674965571357009L;

	public ClienteMatriculaDAO() {
		super(ClienteMatricula.class);
	}

	public List<Matricula> buscarMatriculas(
			Cliente cliente){

		StringBuilder jpql = new StringBuilder();
		jpql.append("Select cm.matricula from ClienteMatricula cm where cm.cliente=:cliente ");
		Query query = em.createQuery(jpql.toString());
		query.setParameter("cliente", cliente);
		
		return query.getResultList();

	}

}
