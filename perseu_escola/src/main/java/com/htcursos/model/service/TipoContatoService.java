package com.htcursos.model.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.dao.TipoContatoDAO;
import com.htcursos.model.entity.TipoContato;
@Service
public class TipoContatoService extends GenericService<TipoContato, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7877213370333717687L;
	@Autowired
	private TipoContatoDAO tipoContatoDAO;
	@Override
	public GenericDAO<TipoContato, Integer> getDao() {
		// TODO Auto-generated method stub
		return tipoContatoDAO;
	}

}
