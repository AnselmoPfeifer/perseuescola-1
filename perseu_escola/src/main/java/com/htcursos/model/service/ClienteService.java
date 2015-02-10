package com.htcursos.model.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htcursos.model.dao.ClienteDAO;
import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Cliente;
@Service
public class ClienteService extends GenericService<Cliente, Integer> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6363780809064576496L;
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Override
	public GenericDAO<Cliente, Integer> getDao() {
		// TODO Auto-generated method stub
		return clienteDAO;
	}

	public Cliente buscarPorCpf(String pfCpf) {
		return clienteDAO.buscarPorCpf(pfCpf);
	}

	public List<Cliente> buscarTodos(String nomeBusca) {
		// TODO Auto-generated method stub
		return clienteDAO.buscarTodos(nomeBusca);
	}
	
	
	@Override
	public Cliente salvar(Cliente cliente) throws ServiceException {
		
		
		
		if(cliente.getContatoList()==null||cliente.getContatoList().size()==0){
			throw new ServiceException("Adicione pelo menos um contato");
			
		}
		
		return super.salvar(cliente);
	}
	
}
