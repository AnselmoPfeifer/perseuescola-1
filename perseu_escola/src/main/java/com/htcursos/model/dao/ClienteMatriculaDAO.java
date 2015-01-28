package com.htcursos.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
			ClienteMatricula clienteMatriculaBusca) {

		StringBuilder jpql = new StringBuilder();
		jpql.append("Select cm.matricula from ClienteMatricula cm where 1=1 ");

		if (clienteMatriculaBusca.getCliente().getNome() != null) {
			jpql.append(" and cm.cliente.nome like :nome");
		}
		if (clienteMatriculaBusca.getCliente().getPfCpf() != null) {
			jpql.append(" and cm.cliente.pfCpf like :cpf");
		}

		Query query = em.createQuery(jpql.toString());

		if (clienteMatriculaBusca.getCliente().getNome() != null||clienteMatriculaBusca.getCliente().getNome() != "") {
			query.setParameter("nome", clienteMatriculaBusca.getCliente()
					.getNome());
		}
		if (clienteMatriculaBusca.getCliente().getPfCpf() != null||clienteMatriculaBusca.getCliente().getPfCpf() != "") {
			query.setParameter("cpf", clienteMatriculaBusca.getCliente()
					.getPfCpf());
		}
		
		return query.getResultList();

	}

}
