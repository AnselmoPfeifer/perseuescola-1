package com.htcursos.model.dao;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Conta;


@Transactional
@Repository
public class ContaDAO extends GenericDAO<Conta, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7432932640772826740L;

	public ContaDAO() {
	super(Conta.class);
	}
	
	/**
	 * Buscando uma conta por codigo Reduzido
	 * @param codigoReduzido 
	 * @return Objeto Conta
	 */
	public Conta buscarPorCodigoReduzido(Integer codigoReduzido) {
		Query query =  em.createQuery("select c from Conta c where c.codigoReduzido=:codRed");
		query.setParameter("codRed",codigoReduzido);		
		return (Conta)query.getSingleResult();
	}
}
