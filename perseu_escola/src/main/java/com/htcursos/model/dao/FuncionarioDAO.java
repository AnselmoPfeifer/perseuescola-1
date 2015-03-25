package com.htcursos.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Funcionario;

@Transactional
@Repository
public class FuncionarioDAO extends GenericDAO<Funcionario, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4796928244980483741L;

	public FuncionarioDAO() {
	super(Funcionario.class);
	}

	public Funcionario buscarPorCpf(String cpf) {
		Query query =  em.createQuery("select c From Funcionario c where c.cpf=:cpfParam");
		query.setParameter("cpfParam", cpf);
		return (Funcionario)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos(String nomeBusca) {
		Query query =  em.createQuery("select c From Funcionario c where upper(c.nome) like :nomeParam");
		query.setParameter("nomeParam","%" +  nomeBusca.toUpperCase() + "%");
		query.setMaxResults(10);
		return query.getResultList();
	}

}
