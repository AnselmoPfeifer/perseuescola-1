package com.htcursos.model.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Parcela;

@Transactional
@Repository
public class ParcelaDAO extends GenericDAO<Parcela, Integer>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3456183926249006300L;

	public ParcelaDAO() {
		super(Parcela.class);
	} 

}
