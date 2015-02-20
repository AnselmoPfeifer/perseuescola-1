package com.htcursos.model.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.htcursos.model.dao.GenericDAO;
import com.htcursos.model.entity.Modelo;


@Service
public abstract class GenericService<T extends Modelo<ID> ,ID extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5979261974245243082L;

	public abstract GenericDAO<T, ID> getDao() ; 
	
	public T salvar(T o ) throws ServiceException{
		try{
			return getDao().salvar(o);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException (e);
		}
	}
	
	public T buscarPorId(ID id){
		return getDao().buscarPorId(id);
	}
	
	public List<T> buscarTodos() {
		return getDao().buscarTodos();
	}
	

	public List<T> buscarTodosIDDescrencente() {
		return getDao().buscarTodosIDDescrencente();
	}
	
	public void excluir(ID id){
		getDao().excluir(id);
	}
	
	public void excluir(T obj){
		getDao().excluir(obj);
	}
	
	public void excluir(List<ID> ids){
		getDao().excluir(ids);
	}
	
}
