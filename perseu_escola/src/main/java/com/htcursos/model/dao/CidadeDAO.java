package com.htcursos.model.dao;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Cidade;




@Transactional
@Repository
public class CidadeDAO extends GenericDAO<Cidade, Integer> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9148047192644712539L;


	public CidadeDAO() {
		super(Cidade.class);
	}

	
	public Cidade buscarPorCidadeEstado(String cidade, String uf) throws Exception {
		try{
			
			Query query = em.createQuery("from Cidade as c " +
													" where upper(c.descricao) = upper(:cidade) " +
													" and upper(c.uf.sigla) = upper(:uf) " +
													" order by c.id");
			
			query.setParameter("cidade", cidade);
			query.setParameter("uf", uf);
			
			if(query.getResultList().size()>0)
				return (Cidade) query.getResultList().get(0);
			else				
				return null;
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}
