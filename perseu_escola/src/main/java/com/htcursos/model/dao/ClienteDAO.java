package com.htcursos.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Cliente;

@Transactional
@Repository
public class ClienteDAO extends GenericDAO<Cliente, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6618119783609145139L;

	public ClienteDAO() {
	super(Cliente.class);
	}

	public Cliente buscarPorCpf(String pfCpf) {
		Query query =  em.createQuery("select c From Cliente c where c.pfCpf=:cpfParam");
		query.setParameter("cpfParam", pfCpf);
		return (Cliente)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos(String nomeBusca) {
		Query query =  em.createQuery("select c From Cliente c where upper(c.nome) like :nomeParam");
		query.setParameter("nomeParam","%" +  nomeBusca.toUpperCase() + "%");
		query.setMaxResults(10);
		return query.getResultList();
	}

}
