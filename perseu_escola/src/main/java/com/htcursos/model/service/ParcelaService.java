package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.ParcelaDAO;
import com.htcursos.model.entity.Parcela;
@Service
public class ParcelaService extends GenericService<Parcela, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7396649201318389188L;
	@Autowired
	private ParcelaDAO parcelaDAO;
	@Override
	public GenericDAO<Parcela, Integer> getDao() {
		// TODO Auto-generated method stub
		return parcelaDAO;
	}

}
