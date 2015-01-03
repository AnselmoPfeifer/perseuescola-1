package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.CartaoDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Cartao;
@Service
public class CartaoService extends GenericService<Cartao, Integer> implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -721356778112229174L;
	@Autowired
	private CartaoDAO cartaoDAO;
	@Override
	public GenericDAO<Cartao, Integer> getDao() {
		// TODO Auto-generated method stub
		return cartaoDAO;
	}

}
