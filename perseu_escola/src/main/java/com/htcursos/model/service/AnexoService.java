package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.AnexoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Anexo;
@Service
public class AnexoService extends GenericService<Anexo, Integer> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AnexoDAO anexoDAO;

	@Override
	public GenericDAO<Anexo, Integer> getDao() {
		return anexoDAO;
	}
	
	@Override
	public Anexo salvar(Anexo o) throws ServiceException {
		// TODO Auto-generated method stub
		return super.salvar(o);
	}
	
	@Override
	public void excluir(Anexo obj) {
		// TODO Auto-generated method stub
		super.excluir(obj);
	}

}
