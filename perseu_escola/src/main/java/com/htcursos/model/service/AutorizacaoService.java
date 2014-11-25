package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.AutorizacaoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Autorizacao;
@Service
public class AutorizacaoService extends GenericService<Autorizacao, Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6618262915754633961L;
	@Autowired
	private AutorizacaoDAO autorizacaoDAO;

	@Override
	public GenericDAO<Autorizacao, Long> getDao() {
		// TODO Auto-generated method stub
		return autorizacaoDAO;
	}

}
