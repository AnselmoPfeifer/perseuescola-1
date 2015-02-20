package com.htcursos.model.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.FuncionarioDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Funcionario;
@Service
public class FuncionarioService extends GenericService<Funcionario, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3336292785478187659L;
	@Autowired
	private FuncionarioDAO FuncionarioDAO;
	
	@Override
	public GenericDAO<Funcionario, Integer> getDao() {
		// TODO Auto-generated method stub
		return FuncionarioDAO;
	}

	public Funcionario buscarPorCpf(String pfCpf) {
		return FuncionarioDAO.buscarPorCpf(pfCpf);
	}

	public List<Funcionario> buscarTodos(String nomeBusca) {
		// TODO Auto-generated method stub
		return FuncionarioDAO.buscarTodos(nomeBusca);
	}
	
	
	@Override
	public Funcionario salvar(Funcionario Funcionario) throws ServiceException {
		
		return super.salvar(Funcionario);
	}
	
}
